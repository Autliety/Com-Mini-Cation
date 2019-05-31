const globalData = {
  onPage: 0
}
/* Global Data:
  wxUser: WeChat user info
  cmcUser: school user info

  qnaList: selfqna data list

  Storage Data:
  token: user session token
 */

export function setData (key: string, val: any) {
  globalData[key] = val
}

export function getData (key: string) {
  return globalData[key]
}
