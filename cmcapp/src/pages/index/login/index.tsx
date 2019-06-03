import Taro from '@tarojs/taro'
import { View, Button } from '@tarojs/components'
import { AtModal, AtModalHeader, AtModalContent, AtModalAction, AtActivityIndicator } from 'taro-ui'

import './index.scss'

import { cmcLogin } from './actions'

type Props = { onLoginSuccess: () => void }

export default class Login extends Taro.Component<Props> {

  state = {
    loading: true
  }

  handleGetUserInfo = () => {
    this.setState({loading: true})
    cmcLogin().then(() => {
      this.props.onLoginSuccess()
    })
  }

  componentWillMount () {
    Taro.getSetting().then(s => {
      if (s.authSetting['scope.userInfo']) {
        this.handleGetUserInfo()
      } else {
        this.setState({loading: false})
      }
    })
  }

  render () {
    return (
      <View className='Login'>
        <AtModal
          isOpened
          closeOnClickOverlay={false}
        >
          <AtModalHeader>欢迎使用通小信</AtModalHeader>
          <AtModalContent>
            <View>欢迎使用杭州电子科技大学通信工程学院师生信息化平台。{'\n'}</View>
            <View>首次登录请授权使用微信个人信息</View>
          </AtModalContent>
          <AtModalAction>
            {this.state.loading ?
              <View className='indicator-container'>
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
