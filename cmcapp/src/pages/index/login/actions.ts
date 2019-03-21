import Taro from '@tarojs/taro'
import cmcRequest from '../../../utils/request'
import { setGlobalData } from '../../../utils/global_data'

export default async function cmcLogin (): Promise<void> {
  console.log('inLogin')
  const loginData = await Taro.login()
  const userData = await Taro.getUserInfo()
  const res = await cmcRequest('POST', '/login',
    {
      code: loginData.code,
      sig: userData.signature,
      raw: userData.rawData
    })
  setGlobalData('sessionId', res.sessionId)
  setGlobalData('userInfo', userData.userInfo)
  setGlobalData('initData', res.data)
}
