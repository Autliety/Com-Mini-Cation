import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'

import './index.scss'

import TabBar from './tabbar'
import getProps from '../../utils/properties'
import Home from './contents/home'
import SelfQna from './contents/selfqna'
import EchoWall from './contents/echowall'
import Appoint from './contents/appoint'
import Login from './login'
import { initData } from './actions'

type State = {
  onTab: number,
  data: GlobalData
}

export default class Index extends Taro.Component<any, State> {

  constructor (props) {
    super(props)
    this.state = {
      onTab: -1,
      data: {} as GlobalData
    }
  }


  config: Taro.Config = {
    navigationBarTitleText: '通小信'
  }

  handleLoginSuccess = () => {
    Taro.startPullDownRefresh()
    .then(() => {
      this.setState({
        onTab: 0
      })
    })
  }

  handleTabChange = (value) => {
    const title = value === 0 ? getProps('appName') : getProps('tabList')[value].title
    Taro.setNavigationBarTitle({title})
    .then(() => {
      this.setState({
        onTab: value
      })
    })
  }

  onPullDownRefresh () {
    initData()
    .then((data) => {
      this.setState({
        data
      })
    })
    .finally(() => {
      Taro.stopPullDownRefresh()
        Taro.showToast({
          icon: 'none',
          title: 'test'
        })

    })
  }

  render () {
    const {cmcUser, docList, aptList, ewList} = this.state.data
    const isTeacher = cmcUser ? cmcUser.roles.indexOf('TEACHER') >= 0 : false

    let content
    switch (this.state.onTab) {
      case -1: {
        content = <Login onLoginSuccess={this.handleLoginSuccess} />
        break
      }
      case 0: {
        content = <Home
          ist={isTeacher}
          data={cmcUser}
          docNum={docList? docList.length : 0}
        />
        break
      }
      case 1: {
        content = <SelfQna data={docList} />
        break
      }
      case 2: {
        content = <EchoWall ist={isTeacher} data={ewList} />
        break
      }
      case 3: {
        content = <Appoint ist={isTeacher} data={aptList} />
        break
      }
    }

    return (
      <View className='home'>
        {content}
        <TabBar onTabChange={this.handleTabChange} />
      </View>
    )
  }
}
