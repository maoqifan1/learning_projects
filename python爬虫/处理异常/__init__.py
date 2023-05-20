from urllib import request, error


def handle_urlerror():
    try:
        response = request.urlopen('https://cuiqingcai.com/index.htm')
    except error.URLError as e:
        print(e.reason)


def handle_httperror():
    try:
        response = request.urlopen('https://cuiqingcai.com/index.htm')
    except error.HTTPError as e:
        print(e.reason, e.code, e.headers, end='\n')


def handle_eror():
    try:
        response = request.urlopen('https://cuiqingcai.com/index.htm')
    # URLError 是 HttpError的父类
    except error.HTTPError as e:
        print(e.reason, e.code, e.headers, end='\n')
    except error.URLError as e:
        print(e.reason)
    else:
        print('request successfully')


if __name__ == '__main__':
    handle_httperror()
