import { getRequest } from '../../utils/request'
import { setData } from '../../utils/globalData'
import { isTeacher } from '../../utils/debuging'

export async function initData (): Promise<void> {
  const userRes = await getRequest('/user')
  setData('cmcUser', userRes.user)

  const qnaRes = await getRequest('/selfqna')
  setData('qnaList', qnaRes.results)

  const ewRes = await getRequest('/echowall', isTeacher() ? undefined : {my: true})
  setData('ewList', ewRes.questions)
}
