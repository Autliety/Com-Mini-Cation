import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtNavBar } from 'taro-ui'
import UserDrawer from './UserDrawer'


type Props = { title: string, user: any }
type State = any

export default class Navigation extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
    this.state = {
      userDrawer: false,
    }
  }

  handleClickList = () => {
    console.log('open option list')
  }

  handleClickUser = () => {
    this.setState({
      userDrawer: true
    })
  }

  handleExitModal = () => {
    this.setState({
      userDrawer: false
    })
  }

  render () {
    return (
      <View className='Navigation'>
        <AtNavBar
          fixed
          leftText={this.props.user.realName}
          leftIconType='user'
          onClickLeftIcon={this.handleClickUser}
          title={this.props.title}
          rightFirstIconType='bullet-list'
          onClickRgIconSt={this.handleClickList}
          // rightSecondIconType='user'
          // onClickRgIconNd={this.handleClickUser}
        />
        <AtNavBar />
        {this.state.userDrawer && <UserDrawer user={this.props.user} onExit={this.handleExitModal} />}
      </View>
    )
  }
}
