import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtCalendar, AtCard } from 'taro-ui'
import './index.scss'

export default class Appoint extends Taro.Component {

  state = {}

  render () {
    return (
      <View className='appoint'>
        <View className='calendar-container'>
          <AtCalendar
            marks={[
              {value: '2019/06/01'}
            ]}
          />
        </View>
        <AtCard
          title='2019年6月1日'
          isFull
          extra='上午'
        >
          <View className='card-content-container'>
            <View>预约内容: 就业指导咨询</View>
            <View>备注: 老师您好我是邬睿航</View>
            <View>教师: 滕旭阳</View>
            <View>状态: 已确认</View>
          </View>
        </AtCard>

      </View>
    )
  }
}
