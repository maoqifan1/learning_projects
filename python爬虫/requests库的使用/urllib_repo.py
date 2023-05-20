import urllib.request
import urllib.parse

if __name__ == "__main__":
    # response = urllib.request.urlopen('https://www.python.org')
    # # print(response.read().decode('utf-8'))
    # print(response.status)
    # # 获取响应头部分的信息
    # print(response.getheaders(), end='/n/t')
    # # 获取服务器信息
    # print(response.getheader('Server'))
    # print(type(response))
    # 使用data参数
    # data = bytes(urllib.parse.urlencode({'world': 'hello'}), encoding='utf8')
    # response = urllib.request.urlopen("http://httpbin.org/post", data=data)
    # print(response.read())

    url = 'http://httpbin.org/post'
    headers = {
        'User-Agent': 'Mozilla/4.0 (compatible;MSIE 5.5;Windows NT)',
        'Host': 'httpbin.org'
    }

    my_dict = {
        'name': 'Germey'
    }
    data = bytes(urllib.parse.urlencode(my_dict), encoding='utf8')
    req = urllib.request.Request(url=url, headers=headers, method='POST')
    response = urllib.request.urlopen(req)
    print(response.read())
