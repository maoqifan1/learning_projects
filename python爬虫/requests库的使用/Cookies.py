import http.cookiejar, urllib.request


def print_cookie():
    cookie = http.cookiejar.CookieJar()
    handler = urllib.request.HTTPCookieProcessor(cookie)
    opener = urllib.request.build_opener(handler)
    response = opener.open('https://www.baidu.com')
    for item in cookie:
        print('{0} = {1}'.format(item.name, item.value))


def write_cookie_to_file():
    filename = 'cookies.txt'
    # cookie = http.cookiejar.MozillaCookieJar(filename)
    # 保存成lwp格式的cookie文件
    cookie = http.cookiejar.LWPCookieJar(filename)
    handler = urllib.request.HTTPCookieProcessor(cookie)
    opener = urllib.request.build_opener(handler)
    response = opener.open('https://www.baidu.com')
    # 忽略过期和销毁时间
    cookie.save(ignore_discard=True, ignore_expires=True)


def read_lwp_cookie_file():
    cookie = http.cookiejar.LWPCookieJar()
    cookie.load('cookies.txt', ignore_discard=True, ignore_expires=True)
    handler = urllib.request.HTTPCookieProcessor(cookie)
    opener = urllib.request.build_opener(handler)
    response = opener.open('http://www.baidu.com')
    print(response.read().decode('utf-8'))


if __name__ == "__main__":
    write_cookie_to_file()
    read_lwp_cookie_file()
