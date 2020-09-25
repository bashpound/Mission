import axios from 'axios'
import errorParser from '@/utils/error-parser'
import eventBus from '@/event-bus'

export default {

  fetchArticles () {
    return new Promise((resolve, reject) => {
      axios.get('/getArticles').then(({ data }) => {
        resolve(data)
        console.log(data)
        eventBus.$emit('articleLoad', data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  writeArticle (detail) {
    return new Promise((resolve, reject) => {
      axios.post({
        url: '/registerArticle',
        data: detail
      }).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  },
  fileUpload (detail) {
    return new Promise((resolve, reject) => {
      axios.post('/fileUpload', detail).then(({ data }) => {
        resolve(data)
      }).catch((error) => {
        reject(errorParser.parse(error))
      })
    })
  }
}
