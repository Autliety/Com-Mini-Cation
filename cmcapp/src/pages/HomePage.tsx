import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtCard } from 'taro-ui'

type Props = any
type State = any

export default class HomePage extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
  }

  render () {
    return (
      <View className='HomePage'>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
        <AtCard
          note='小Tips'
          extra='额外信息'
          title='标题'
        >
          界面开发中
        </AtCard>
      </View>
    )
  }
}
