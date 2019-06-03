const globalData = {
  onPage: 0
}
/*
  Storage Data:
  token: user session token
*/

/*
  Global Data:
  wxUser: WeChat user info
  cmcUser: school user info
  qnaList: selfqna data list
  ewList: echoWall list

 */

export function setData (key: string, val: any) {
  globalData[key] = val
}

export function getData (key: string) {
  return globalData[key]
}
