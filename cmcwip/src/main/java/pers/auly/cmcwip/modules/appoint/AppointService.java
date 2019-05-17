package pers.auly.cmcwip.modules.appoint;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import pers.auly.cmcwip.security.user.User;
import pers.auly.cmcwip.security.user.UserRepository;
import pers.auly.cmcwip.security.user.UserRole;
import pers.auly.cmcwip.utils.SessionUtils;
import pers.auly.cmcwip.utils.exceptions.BadRequestException;
import pers.auly.cmcwip.utils.exceptions.ForbiddenException;
import pers.auly.cmcwip.utils.exceptions.NoSuchUserException;
import pers.auly.cmcwip.utils.exceptions.NotFoundException;

@Service
class AppointService {
    
    private final AppointRepository appointRepository;
    private final UserRepository userRepository;
    
    private AppointService(AppointRepository appointRepository,
        UserRepository userRepository) {
        this.appointRepository = appointRepository;
        this.userRepository = userRepository;
    }
    
    Collection<User> getTeachers() {
        return userRepository.findAllByRolesContains(UserRole.TEACHER);
    }
    
    List<Appoint> getMyAppoints(boolean isTeacher) {
        if (isTeacher) {
            return appointRepository.findAllByDistUserOrderByAppointDate(SessionUtils.current());
        } else {
            return appointRepository.findAllByUserOrderByAppointDate(SessionUtils.current());
        }
    }
    
    void createAppoint(String summary, int distId, Date date, AppointTime time) {
        User distUser = userRepository.findById(distId)
            .orElseThrow(NoSuchUserException::new);
        if (!distUser.getRoles().contains(UserRole.TEACHER)) {
            throw new BadRequestException();
        }
        Appoint appoint = Appoint.builder()
            .user(SessionUtils.current())
            .appointDate(date)
            .time(time)
            .distUser(distUser)
            .summary(summary)
            .status(AppointStatus.NEW)
            .build();
        appointRepository.save(appoint);
    }
    
    void respondAppoint(int aid, AppointStatus status) {
        Appoint appoint = appointRepository.findById(aid)
            .orElseThrow(NotFoundException::new);
        if (!appoint.getDistUser().getId().equals(SessionUtils.current().getId())) {
            throw new ForbiddenException();
        }
        appoint.setStatus(status);
        appointRepository.save(appoint);
    }
}
