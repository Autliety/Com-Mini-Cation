import Taro from '@tarojs/taro'
import {View, Text, Button} from '@tarojs/components'
import './index.less'

const APP = Taro.getApp();

export default class Index extends Taro.Component {

  constructor(props) {
    super(props);
    this.config = {
      navigationBarTitleText: 'index'
    };
    this.state = {
      nickName: 'Guest',
      number: undefined
    };
  }

  componentWillMount() {
    Taro.getUserInfo({
      success: res => {
        APP.globalData = {
          userInfo: res.userInfo
        };
        this.setState({
          nickName: res.userInfo.nickName
        });
        Taro.request({
          url: 'http://localhost:8080/hello',
          method: 'POST',
          data: {name: res.userInfo.nickName}
        }).then(res => {
          this.setState({number: res.data.number})
        })
      },

      fail: () => {
        this.setState({userInfo: 'Guest'})
      }

    })
  }

  render() {
    return (
      <View>
        <Text>Hello, {this.state.nickName}: </Text>
        {this.state.number === undefined ?
          <Button openType={'getUserInfo'}
                  onGetUserInfo={this.componentWillMount}>Login</Button>
          :
          <Text>You are the&nbsp;{this.state.number}&nbsp;one to login</Text>
        }
      </View>
    )
  }
}


