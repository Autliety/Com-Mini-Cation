export async function sleep (ms: number) {
  console.info('begin sleep for ' + ms + 'ms')
  await new Promise(resolve => setTimeout(resolve, ms))
  console.info('awaken')
}

export function convertDate (date: string) {
  return date.replace(/\//, '年').replace(/\//, '月') + '日'
}
