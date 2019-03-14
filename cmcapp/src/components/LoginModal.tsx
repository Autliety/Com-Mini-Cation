import Taro from '@tarojs/taro'
import { Button, View } from '@tarojs/components'
import { AtModal, AtModalAction, AtModalContent, AtModalHeader } from 'taro-ui'

type Props = { onHandleLogin: () => void }
type State = any

export default class LoginModal extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
    this.state = {
      open: true
    }
  }

  handleClick = () => {
    this.props.onHandleLogin()
  }

  render () {
    return (
      <View className='LoginModal'>
        <AtModal isOpened={this.state.open}
                 closeOnClickOverlay={false}
        >
          <AtModalHeader>Welcome</AtModalHeader>
          <AtModalContent>
            Welcome to Com-Mini-Cation!
            Click the button below to enter:
          </AtModalContent>
          <AtModalAction>
            <Button onClick={this.handleClick} openType='getUserInfo'>
              使用微信登录
            </Button>
          </AtModalAction>
        </AtModal>
      </View>
    )
  }
}
