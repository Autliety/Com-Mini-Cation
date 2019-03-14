import Taro from '@tarojs/taro'

export default async function cmcLogin () {
  let user: any = await Taro.getUserInfo()
  let info = user.userInfo
  let data = {
    sig: user.signature,
    name: info.nickName
  }
  let res: any = await Taro.request({
    url: 'http://172.20.10.3:8080/login',
    method: 'POST',
    data
  })
  info['realName'] = res.data.name
  info['freq'] = res.data.freq
  return info
}
