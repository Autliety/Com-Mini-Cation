import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtCalendar, AtCard, AtActionSheet, AtActionSheetItem } from 'taro-ui'
import './index.scss'
import { convertDate, sleep } from '../../../../utils/debuging'
import { checkAppoint } from './actions'

type Props = {
  ist: boolean,
  data: Aptment[]
}

export default class Appoint extends Taro.Component<Props> {

  state = {
    inPick: '',
    hasApt: false,
    current: {} as Aptment,
    action: false
  }

  componentDidMount () {
    this.handleDayClick({value: new Date().toLocaleDateString()})
  }

  handleDayClick = (item) => {
    const current = this.props.data.find(apt => apt.appointDate === item.value)
    this.setState({
      inPick: item.value,
      hasApt: !!current,
      current,
    })
  }

  handleClickCard = () => {
    if (this.props.ist && this.state.current.status !== 'CONFIRMED') {
      this.setState({
        action: true
      })
    }
  }

  handleClickCreate = () => {
    if (!this.props.ist) {
      Taro.navigateTo({url: '/pages/appoint/new?date=' +this.state.inPick})
    }
  }

  handleChangeAction = (y: boolean) => {
    checkAppoint(this.state.current.id, y)
    .then(() => Taro.startPullDownRefresh()
    .then(() => this.handleDayClick({value: this.state.inPick})))
    this.setState({
      action: false
    })
  }

  handleCancelAction = () => {
    this.setState({
      action: false
    })
  }

  componentDidShow () {
    Taro.startPullDownRefresh()
    .then(() => {
      sleep(1000)
      .then(() => {
        this.handleDayClick({value: this.state.inPick})
      })
    })
  }

  render () {
    const {ist, data} = this.props
    const {inPick, current, hasApt} = this.state

    const cardMsg = ist ? '当天没有预约信息' : '点击此处添加当天的预约'
    let name, phone, status, note
    if (hasApt) {
      name = ist ? current.user.realName : current.distUser.realName
      phone = ist ? current.user.phoneNum : current.distUser.phoneNum
      status = current.status === 'NEW' ? '未确认' :
        current.status === 'CONFIRMED' ? '已确认' : '已拒绝'
      if (ist && current.status !== 'CONFIRMED' )
      note = '点击更改预约状态'
    }

    return (
      <View className='appoint'>
        <View className='calendar-container'>
          <AtCalendar
            marks={data.map(apt => {
              return {value: apt.appointDate}
            })}
            onDayClick={this.handleDayClick}
            format='YYYY/M/D'
          />
        </View>

        {hasApt ?
          <AtCard
            isFull
            title={convertDate(inPick)}
            extra={current.time}
            onClick={this.handleClickCard}
            note={note}
          >
            <View className='card-content-container'>
              <View>预约内容: {current.summary}</View>
              <View>预约状态: {status}</View>
              <View> </View>
              <View>{ist ? '学生' : '教师'}: {name}</View>
              <View>联系电话: {phone}</View>
            </View>
          </AtCard>
          :
          <AtCard
            isFull
            title={convertDate(inPick)}
            onClick={this.handleClickCreate}
          >
            <View className='card-content-container'>{cardMsg}</View>
          </AtCard>
        }

        <AtActionSheet
          isOpened={this.state.action}
          cancelText='取消'
          title='已确认的预约将无法修改状态'
          onCancel={this.handleCancelAction}
          onClose={this.handleCancelAction}
        >
          <AtActionSheetItem onClick={() => this.handleChangeAction(true)}>
            确认预约
          </AtActionSheetItem>
          <AtActionSheetItem onClick={() => this.handleChangeAction(false)}>
            拒绝预约
          </AtActionSheetItem>
        </AtActionSheet>

      </View>
    )
  }
}
