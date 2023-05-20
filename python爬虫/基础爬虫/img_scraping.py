from urllib.request import urlretrieve
from urllib.request import urlopen
from bs4 import BeautifulSoup
import os
import ssl

ssl._create_default_https_context = ssl._create_unverified_context


# 爬取文档
def get_text(url) -> str:
    html = urlopen(url)
    s = ''
    bs_obj = BeautifulSoup(html, features='lxml')
    # print(bs_obj.get_text)
    for obj in bs_obj.find("div", {"id": "pageNo-1"}).findAll('p'):
        print(obj.get_text())
        s += obj.get_text()
    for obj in bs_obj.find("div", {"id": "pageNo-2"}).findAll('p'):
        print(obj.get_text())
        s += obj.get_text()
    return s  # 返回字符串


# 爬取图片
def get_img_src(url):
    img_list = []
    html = urlopen(url)
    bs_obj = BeautifulSoup(html, features='lxml')
    for bos in bs_obj.findAll('img'):
        if 'src' in bos.attrs:
            img_list.append(url + bos.attrs['src'])
    return img_list


# 下载图片
def download_image(img_list, path):
    x = 0
    if not os.path.isdir(path):  # 如果是一个路径,就将图片进行保存
        os.makedirs(path)  # 将图片保存到指定路径下，如果没有文件则创建
    paths = path + '/'  # 保存在test路径下
    for img_Item in img_list:
        urlretrieve(img_Item, '{0}{1}.jpg'.format(paths, x))
        x += 1
        print("图片" + str(x) + "下载成功")


def write_to_file(url, str):
    with open(url, 'w') as f_boj:
        f_boj.write(str)


if __name__ == "__main__":
    write_to_file('/Users/maoqifan/Documents/1.txt',
                  get_text("https://wenku.baidu.com/view/a9789a718ad63186bceb19e8b8f67c1cfbd6ee23.html"))
    # lis1 = get_img_src("http://www.nuist.edu.cn/")
    # Path = '../../../../Desktop/下载图片'  # 设置保存地址
    # download_image(lis1, Path)
