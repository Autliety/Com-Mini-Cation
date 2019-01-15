import Taro from '@tarojs/taro'
import {View, Text} from '@tarojs/components'
import {AtButton, AtIcon} from 'taro-ui'
import './index.scss'

const APP = Taro.getApp();

const URL = 'http://localhost:8080';

export default class Index extends Taro.Component {

  constructor(props) {
    super(props);
    this.config = {
      navigationBarTitleText: 'index'
    };
    this.state = {
      nickName: 'Guest',
      id: undefined,
      freq: undefined
    };
  }

  componentWillMount() {

  }

  handleClick() {
    Taro.getUserInfo()
    .then(result => {
      APP.globalData = {
        userInfo: result.userInfo
      };
      this.setState({
        nickName: result.userInfo.nickName
      });
      let option = {
        url: URL + '/hello',
        method: 'GET',
        data: {name: result.userInfo.nickName}
      };
      Taro.request(option)
      .then(response => {
        this.setState({
          id: response.data.id,
          freq: response.data.freq
        });
      })
      .catch(err => {
        console.log('netErr', err);
      })
    })
    .catch(err => {
      console.log('userErr', err);
    });
  }

  render() {
    return (
      <View className='at-article'>
        <AtIcon value='user' color='green' size={30} />
        <Text className='at-article__h1'>Hello, {this.state.nickName}: </Text>
        {this.state.id === undefined ?
          <AtButton type='primary' circle openType='getUserInfo'
                    onClick={this.handleClick}>
            Login
          </AtButton>
          :
          <View className='at-article__h3'>
            <View>Your user ID: {this.state.id}</View>
            <View>Your logging frequency: {this.state.freq}</View>
          </View>
        }
      </View>
    )
  }
}

