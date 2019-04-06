import Taro from '@tarojs/taro'
import { View, Button } from '@tarojs/components'
import { AtModal, AtModalHeader, AtModalContent, AtModalAction, AtActivityIndicator } from 'taro-ui'

import './Login.scss'

import cmcLogin from './actions'

type Props = { onLogin: () => void }

export default class Login extends Taro.Component<Props> {

  state = {
    open: true,
    loading: true
  }

  componentDidMount () {
    Taro.getSetting().then(s => {
      if (s.authSetting['scope.userInfo']) {
        this.handleGetUserInfo()
      } else {
        this.setState({open: true, loading: false})
      }
    })
  }

  componentWillUnmount (): void {
    console.log('unmount')
  }

  handleGetUserInfo = () => {
    this.setState({loading: true})
    cmcLogin().then(() => {
      this.props.onLogin()
      this.setState({open: false, loading: false})
    })
  }

  render () {
    return (
      <View className='Login'>
        <AtModal
          isOpened={this.state.open}
          closeOnClickOverlay={false}
        >
          <AtModalHeader>Welcome</AtModalHeader>
          <AtModalContent>
            Welcome to Com-Mini-Cation!\n\r
            If you are new here,\n\r
            Click the button below to enter:
          </AtModalContent>
          <AtModalAction>
            {this.state.loading ?
              <View className='indicator'>
                <AtActivityIndicator mode='center' content='loading...' />
              </View>
              :
              <Button
                onGetUserInfo={this.handleGetUserInfo}
                openType='getUserInfo'
              >
                使用微信登录
              </Button>
            }
          </AtModalAction>
        </AtModal>

      </View>
    )
  }
}
