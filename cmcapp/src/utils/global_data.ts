const globalData = {
  APP_NAME: '通小信WIP',
  TAB_LIST: [
    {title: '主页', iconType: 'home'},
    {title: '自助问答', iconType: 'tags'},
    {title: '预约服务', iconType: 'clock'},
    {title: '回音壁', iconType: 'bell'}
  ]
}

export function setGlobalData (key: string, val: any) {
  globalData[key] = val
}

export function getGlobalData (key) {
  return globalData[key]
}
