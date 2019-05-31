import { getRequest } from '../../utils/request'
import { setData } from '../../utils/globalData'

export async function initData () : Promise<void> {
  const qnaRes = await getRequest('/selfqna')
  setData('qnaList', qnaRes.results)
}
