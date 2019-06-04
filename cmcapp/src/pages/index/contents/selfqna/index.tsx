import Taro from '@tarojs/taro'
import { View } from '@tarojs/components'
import { AtDivider, AtList, AtListItem, AtSearchBar } from 'taro-ui'
// import './index.scss'
import Picks from './picks'
import { search } from './actions'

type Props = {
  data: Document[]
}

type State = {
  value: string,
  isSearch: boolean,
  docs: Document[],
  inPick: Document
}

const PICK = {id: -1} as Document

export default class SelfQna extends Taro.Component<Props, State> {

  constructor (props) {
    super(props)
    this.state = {
      value: '',
      isSearch: false,
      docs: [],
      inPick: PICK
    }
  }

  handleTextChange = (value) => {
    this.setState({
      value,
      isSearch: value !== '',
      docs: [],
      inPick: PICK
    })
  }

  handleClickSearch = (key: string) => {
    search(key)
    .then((searchList) => {
      if (searchList.length === 0) {
        Taro.showToast({
          title: '找不到相关内容',
          icon: 'none',
          duration: 2000
        })
      } else {
        this.setState({
          docs: searchList
        })
      }
    })
  }

  handleClickList = (value) => {
    this.setState({
      inPick: value
    })
  }

  handlePickExit = () => {
    this.setState({
      inPick: PICK
    })
  }

  render () {
    const {value, isSearch, docs, inPick} = this.state

    const docList = isSearch ? docs : this.props.data

    return (
      <View className='selfqna'>
        <View className='search'>
          <AtSearchBar
            value={value}
            onChange={this.handleTextChange}
            onActionClick={() => this.handleClickSearch(value)}
          />
        </View>

        {docList.length !== 0 &&
        <AtDivider content={isSearch ? '搜索结果' : '最近更新'} />
        }

        <AtList>
          {docList.map((d, index) => {
            return <AtListItem
              key={index}
              title={d.title}
              note={d.fileName}
              arrow='right'
              onClick={() => this.handleClickList(d)}
            />
          })}
        </AtList>

        <Picks
          inPick={inPick}
          onExit={this.handlePickExit}
        />

      </View>
    )
  }
}
