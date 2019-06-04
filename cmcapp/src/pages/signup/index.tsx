import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtDivider, AtSwitch, AtButton, AtInput } from 'taro-ui'
import { signUp } from './actions'
//import './index.scss'

type Props = any

export default class Index extends Taro.Component<Props> {

  config: Taro.Config = {
    navigationBarTitleText: '用户注册'
  }

  state = {
    name: '',
    number: '',
    phone: '',
    checked: false
  }

  handleNameChange = (value) => {
    this.setState({
      name: value
    })
  }

  handleNumChange = (value) => {
    this.setState({
      number: value
    })
  }

  handlePhoChange = (value) => {
    this.setState({
      phone: value
    })
  }

  handleSwitchChange = (value) => {
    this.setState({
      checked: value
    })
  }

  handleClickSubmit = () => {
    const {name, number, phone, checked} = this.state
    Taro.login()
    .then((data) => {
      signUp({
        realName: name,
        schoolId: number,
        phoneNum: phone,
        isTeacher: checked
      }, data.code)
      .then(() => {
        Taro.reLaunch({url: '/pages/index/index'})
      })
    })
  }

  render () {
    return (
      <View className='index'>

        <AtDivider content='请填写真实个人信息' />

        <AtInput
          name='name'
          title='姓名: '
          placeholder='请填写真实姓名'
          value={this.state.name}
          onChange={this.handleNameChange}
        />

        <AtInput
          name='number'
          type='number'
          title='编号: '
          maxLength={8}
          placeholder='请输入学号/教工号'
          value={this.state.number}
          onChange={this.handleNumChange}
        />

        <AtInput
          name='phone'
          type='number'
          title='联系电话: '
          placeholder='请输入常用联系电话号码'
          value={this.state.phone}
          onChange={this.handlePhoChange}
        />

        <View style='position:fixed;bottom:0;width:100%;'>
          <AtSwitch
            title='我是教师用户'
            checked={this.state.checked}
            onChange={this.handleSwitchChange}
          />
          <AtButton
            type='primary'
            onClick={this.handleClickSubmit}
          >
            确认信息并注册
          </AtButton>
        </View>
      </View>
    )
  }
}
