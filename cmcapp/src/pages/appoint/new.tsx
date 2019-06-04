import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtButton, AtRadio, AtDivider, AtTextarea } from 'taro-ui'
import { getTeachers, newAppoint } from './actions'
import { convertDate } from '../../utils/debuging'
//import './new.scss'

type Props = any

export default class Index extends Taro.Component<Props> {

  config: Taro.Config = {
    navigationBarTitleText: '创建预约服务'
  }

  state = {
    timeValue: 'AM' as 'AM' | 'PM',
    teachers: [] as CmcUser[],
    tValue: -1,
    contentValue: ''
  }

  handleClickRadio = (value) => {
    this.setState({
      timeValue: value
    })
  }

  handleClickTeacher = (value) => {
    this.setState({
      tValue: value
    })
  }

  handleContentChange = (e) => {
    this.setState({
      contentValue: e.target.value
    })

  }

  handleClickSubmit = () => {
    const {tValue, timeValue, contentValue} = this.state
    if (tValue < 0 || contentValue === '') {
      Taro.showToast({
        icon: 'none',
        title: '请选择或填写所有内容'
      })
    } else {
      newAppoint({
        tid: tValue,
        time: timeValue,
        date: this.$router.params.date,
        summary: contentValue
      })
      .then(() => {
        Taro.navigateBack()
      })
    }
  }

  componentWillMount () {
    getTeachers()
    .then((teachers) => {
      this.setState({
        teachers
      })
    })

  }

  render () {
    const teacherOptions = this.state.teachers.map((t) => {
      return {label: t.realName, value: t.id}
    })

    return (
      <View className='index'>

        <AtDivider content='预约日期' />

        <View style='text-align:center'>{convertDate(this.$router.params.date)}</View>

        <AtDivider content='选择预约时间段' />

        <AtRadio
          value={this.state.timeValue}
          options={[
            {label: '上午', value: 'AM'},
            {label: '下午', value: 'PM'}
          ]}
          onClick={this.handleClickRadio}
        />

        <AtDivider content='选择预约教师' />

        <AtRadio
          value={this.state.tValue}
          options={teacherOptions}
          onClick={this.handleClickTeacher}
        />

        <AtDivider content='填写预约内容' />

        <View style='margin:20px;margin-top:0;'>
          <AtTextarea
            value={this.state.contentValue}
            onChange={this.handleContentChange}
            maxLength={60}
            placeholder='简述预约的主要内容和目的。。。'
          />
        </View>

        <View style='position:fixed;bottom:0;width:100%;'>
          <AtButton
            type='primary'
            onClick={this.handleClickSubmit}
          >
            发起预约
          </AtButton>
        </View>

      </View>
    )
  }
}
