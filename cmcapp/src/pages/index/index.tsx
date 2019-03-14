import Taro, { Component, Config } from '@tarojs/taro'
import { View } from '@tarojs/components'
import BottomTab from '../../components/BottomTab'
import Navigation from '../../components/Navigation'
import LoginModal from '../../components/LoginModal'
import cmcLogin from '../../utils/user_util'
import HomePage from '../HomePage'

export default class Index extends Component {

  state = {
    user: 'none',
    page: 0
  }

  config: Config = {
    navigationBarTitleText: '通小信WIP',
  }

  handleLogin = () => {
    cmcLogin().then((u) => {
      this.setState({
        user: u
      })
    })
  }

  handleChange = (index: number) => {
    this.setState({
      page: index
    })
  }

  render () {
    return (
      <View className='index'>
        {this.state.user === 'none' && <LoginModal onHandleLogin={this.handleLogin} />}
        <Navigation title='主页' user={this.state.user} />
        {this.state.page === 0 ? <HomePage /> : <View>页面开发中</View>}
        <BottomTab active={this.state.page} onChange={this.handleChange} />
      </View>
    )
  }
}

