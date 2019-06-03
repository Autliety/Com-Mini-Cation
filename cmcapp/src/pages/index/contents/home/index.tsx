import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtAvatar, AtCard, AtDivider } from 'taro-ui'
import { getData } from '../../../../utils/globalData'
import getProps from '../../../../utils/properties'
import { isTeacher } from '../../../../utils/debuging'

import './index.scss'

type Props = any

export default class Home extends Taro.Component<Props> {

  render () {
    const {realName, schoolId, phoneNum} = getData('cmcUser')
    const qnaNum = getData('qnaList').length
    const tabList = getProps('tabList')

    const role = isTeacher() ? '教师' : '学生'
    return (
      <View className='index'>
        <AtDivider content='个人信息' />
        <View style='display:flex;'>
          <View className='home-avatar'>
          <AtAvatar
            image={getData('wxUser').avatarUrl}
            size='large'
            circle
          />
          </View>
        </View>
        <AtCard
          title={role + ' ' + realName + '，您好！'}
          note='暂不支持修改个人信息'
        >
          <View>学号/教工号：{schoolId}</View>
          <View>联系电话：{phoneNum}</View>
        </AtCard>

        <AtDivider content='今日摘要' />

        <AtCard title={tabList[1].title}>
          {qnaNum > 0 ?
            <View>有{qnaNum}篇最新更新的自助问答文档</View>
            :
            <View>暂无新增的自助问答内容</View>
          }
        </AtCard>

        <AtCard title={tabList[3].title}>
          <View>预约服务有确认状态更新</View>
        </AtCard>
      </View>
    )
  }
}
