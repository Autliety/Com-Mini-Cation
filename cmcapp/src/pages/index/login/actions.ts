import Taro from '@tarojs/taro'
import { getRequest, postRequest } from '../../../utils/request'
import { setData } from '../../../utils/globalData'

export async function cmcLogin (): Promise<void> {
  const userData = await Taro.getUserInfo()
  setData('wmUser', userData.userInfo)

  let user
  try {
    await Taro.checkSession()
    const response = await getRequest('/user')
    user = response.user

  } catch (e) {
    const loginData = await Taro.login()
    const response = await postRequest('/user/login',
      {
        code: loginData.code,
        sign: userData.signature,
        rawData: userData.rawData
      })
    Taro.setStorageSync('token', response.token)
    user = response.user
  }
  setData('cmcUser', user)
}

