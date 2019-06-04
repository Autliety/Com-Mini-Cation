import { getRequest } from '../../utils/request'

export async function initData (): Promise<GlobalData> {
  const userRes = await getRequest('/user')
  const cmcUser = userRes.user

  const docRes = await getRequest('/selfqna')
  const docList = docRes.results

  const ewRes = await getRequest('/echowall')
  const ewList = ewRes.questions

  const aptRes = await getRequest('/appoint')
  const aptList = aptRes.appoints

  return {
    cmcUser,
    docList,
    ewList,
    aptList
  }
}


