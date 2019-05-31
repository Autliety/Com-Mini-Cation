import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtFloatLayout, AtButton } from 'taro-ui'
import { download } from './actions'
import { sleep } from '../../../../utils/debuging'

import './picks.scss'

type Props = {
  picks: {
    id: number,
    title: string,
    summary: string,
    fileName: string
  },
  onExit: () => void
}

export default class Picks extends Taro.Component<Props> {

  state = {
    open: false,
    loading: false
  }

  handleClose = () => {
    this.setState({
      open: false
    })
    this.props.onExit()
  }

  handleClick = () => {
    this.setState({
      loading: true
    })
    download(this.props.picks.id)
    .then(() => {
      sleep(2000).then(() => {
        this.setState({
          open: false,
          loading: false
        })
      })
    })
  }

  componentWillReceiveProps (nextProps: Readonly<Props>) {
    if (nextProps.picks.id !== -1) {
      this.setState({
        open: true
      })
    }
  }

  render () {
    const {picks} = this.props
    return (
      <View className='picks'>
        <AtFloatLayout
          isOpened={this.state.open}
          title={picks.title}
          onClose={this.handleClose}
        >
          {picks.summary.split(' ').map((s, index) => {
            return <View key={index}>{s}</View>
          })}
          <View className='dl-button'>
            <AtButton
              type='primary'
              circle
              loading={this.state.loading}
              onClick={this.handleClick}
            >
              下载并查看 {picks.fileName}
            </AtButton>
          </View>
        </AtFloatLayout>
      </View>
    )
  }
}
