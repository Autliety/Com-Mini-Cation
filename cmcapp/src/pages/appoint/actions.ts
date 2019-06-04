import { getRequest, putRequest } from '../../utils/request'

export async function getTeachers () {
  const res = await getRequest('/appoint/teachers')
  return res.teachers
}

type Prop = {
  tid: number,
  time: 'AM' | 'PM',
  date: string,
  summary: string
}

export async function newAppoint (data: Prop) {
  await putRequest('/appoint', data)
}
