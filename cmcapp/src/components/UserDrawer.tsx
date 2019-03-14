import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtDrawer, AtAvatar } from 'taro-ui'

type Props = { user: any, onExit: () => void }
type State = any

export default class UserDrawer extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
  }

  handleClose = () => {
    this.props.onExit()
  }

  render () {
    return (
      <View className='UserDrawer'>
        <AtDrawer
          show
          mask
          onClose={this.handleClose}
        >
          <AtAvatar circle image={this.props.user.avatarUrl} />
          <View>{this.props.user.nickName}</View>
          <View>{this.props.user.realName}, 你好！</View>
          <View>这是你第{this.props.user.freq}次登录通小信</View>
        </AtDrawer>

      </View>
    )
  }
}
