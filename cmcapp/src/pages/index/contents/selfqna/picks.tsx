import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtFloatLayout, AtButton } from 'taro-ui'
import { download } from './actions'
import { sleep } from '../../../../utils/debuging'

import './picks.scss'

type Props = {
  inPick: Document,
  onExit: () => void
}

export default class Picks extends Taro.Component<Props> {

  state = {
    loading: false
  }

  handleClose = () => {
    this.setState({
      loading: false
    })
    this.props.onExit()
  }

  handleClick = () => {
    this.setState({
      loading: true
    })
    download(this.props.inPick.id)
    .then(() => {
      sleep(2000).then(() => {
        this.handleClose()
      })
    })
  }

  render () {
    const {inPick} = this.props
    return (
      <View className='picks'>
        <AtFloatLayout
          isOpened={inPick.id >= 0}
          title={inPick.title}
          onClose={this.handleClose}
        >
          {inPick.summary.split(' ').map((s, index) => {
            return <View key={index}>{s}</View>
          })}
          <View className='dl-button'>
            <AtButton
              type='primary'
              circle
              loading={this.state.loading}
              onClick={this.handleClick}
            >
              下载并查看 {inPick.fileName}
            </AtButton>
          </View>
        </AtFloatLayout>
      </View>
    )
  }
}
