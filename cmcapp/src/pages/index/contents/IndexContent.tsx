import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import HomePage from './home/HomePage'
import SelfQaPage from './selfqa/SelfQaPage'
import AppointPage from './appoint/AppointPage'
import EchoWallPage from './echowall/EchoWallPage'

type Props = { active: number }

export default class IndexContent extends Taro.Component<Props> {


  render () {
    let content
    switch (this.props.active) {
      case 0: {
        content = <HomePage />
        break
      }
      case 1: {
        content = <SelfQaPage />
        break
      }
      case 2: {
        content = <AppointPage />
        break
      }
      case 3: {
        content = <EchoWallPage />
        break
      }
    }
    return (
      <View className='IndexContent'>
        {content}
      </View>
    )
  }
}
