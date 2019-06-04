import { putRequest } from '../../utils/request'

type Prop = {
  realName: string,
  schoolId: string,
  phoneNum: string,
  isTeacher: boolean
}

export async function signUp (data: Prop, code: string) {
  data['code'] = code
  console.log(data)
  await putRequest('/user', data)
}
