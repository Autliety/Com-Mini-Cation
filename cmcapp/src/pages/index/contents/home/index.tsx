import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtCard, AtDivider } from 'taro-ui'
import { getData } from '../../../../utils/globalData'
import getProps from '../../../../utils/properties'

//import './index.scss'

type Props = any

export default class Home extends Taro.Component<Props> {

  state = {
    role: 'STUDENT',
    name: '0',
    sid: '00000000',
    tel: '00000000000',
    qnaNum: 0
  }

  componentWillMount () {
    const user = getData('cmcUser')
    this.setState({
      role: user.roles[0],
      name: user.realName,
      sid: user.schoolId,
      tel: user.phoneNum,
      qnaNum: getData('qnaList').length
    })
  }

  render () {
    const {name, sid, tel, qnaNum} = this.state
    const roleName = this.state.role === 'STUDENT' ? '学生' : '教师'
    return (
      <View className='index'>
        <AtDivider content='个人信息' />
        <AtCard
          title={roleName + ' ' + name + '，您好！'}
          note='暂不支持修改个人信息'
        >
          <View>学号/教工号：{sid}</View>
          <View>联系电话：{tel}</View>
        </AtCard>

        <AtDivider content='今日摘要' />

        <AtCard title={getProps('tabList')[1].title}>
          {qnaNum > 0 ?
            <View>有{this.state.qnaNum}篇最新更新的自助问答文档</View>
            :
            <View>暂无新增的自助问答内容</View>
          }
        </AtCard>

      </View>
    )
  }
}
