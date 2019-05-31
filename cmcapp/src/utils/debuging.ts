export async function sleep (ms: number) {
  console.info('begin sleep for ' + ms + 'ms')
  await new Promise(resolve => setTimeout(resolve, ms))
  console.info('awaken')
}
