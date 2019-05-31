const properties = {
  appName: '通小信',
  tabList: [
    {title: '主页', iconType: 'home'},
    {title: '自助问答', iconType: 'search'},
    {title: '回音壁', iconType: 'message'},
    {title: '预约服务', iconType: 'calendar'}
  ]
}

export default function getProps (key: string) {
  return properties[key]
}
