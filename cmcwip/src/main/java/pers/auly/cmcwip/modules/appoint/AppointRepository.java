package pers.auly.cmcwip.modules.appoint;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import pers.auly.cmcwip.security.user.User;

interface AppointRepository extends CrudRepository<Appoint, Integer> {
    
    List<Appoint> findAllByUserOrderByAppointDate(User user);
    
    List<Appoint> findAllByDistUserOrderByAppointDate(User distUser);
    
    boolean existsByAppointDateAndTime(Date date, AppointTime time);
}
