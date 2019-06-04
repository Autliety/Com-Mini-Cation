import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtAvatar, AtCard, AtDivider } from 'taro-ui'
import getProps from '../../../../utils/properties'

import './index.scss'

type Props = {
  ist: boolean,
  data: CmcUser,
  docNum: number
}

export default class Home extends Taro.Component<Props> {

  tabList = getProps('tabList')

  render () {
    const {ist, data, docNum} = this.props

    const role = ist? '教师' : '学生'

    return (
      <View className='index'>
        <AtDivider content='个人信息' />

        <View style='display:flex;'>
          <View className='home-avatar'>
            <AtAvatar
              openData={{type: 'userAvatarUrl'}}
              size='large'
              circle
            />
          </View>
        </View>

        <AtCard
          title={role + ' ' + data.realName + '，您好！'}
          note='暂不支持修改个人信息'
        >
          <View>学号/教工号：{data.schoolId}</View>
          <View>联系电话：{data.phoneNum}</View>
        </AtCard>

        <AtDivider content='今日摘要' />

        <AtCard title={this.tabList[1].title}>
          {docNum > 0 ?
            <View>有{docNum}篇最新更新的自助问答文档</View>
            :
            <View>暂无新增的自助问答内容</View>
          }
        </AtCard>

      </View>
    )
  }
}
