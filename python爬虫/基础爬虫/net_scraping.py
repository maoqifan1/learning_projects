from bs4 import BeautifulSoup
from urllib.request import urlopen
from urllib.parse import urlparse
import random
import re
import datetime
import ssl

pages = set()
random.seed(datetime.datetime.now())
all_ext_links = set()
all_int_links = set()


ssl._create_default_https_context = ssl._create_unverified_context


# 获取页面所有内链的列表
def get_internal_links(bs_obj, include_url):
    include_url = urlparse(include_url).scheme + "://" + urlparse(include_url).netloc
    internal_links = []
    # 找出所有以"/"开头的链接
    for link in bs_obj.findAll('a', href=re.compile("^(/|.*" + include_url + ")")):
        if link.attrs['href'] is not None:
            # 判断是否是重复链接
            if link.attrs['href'] not in internal_links:
                # 判断链接是否以 '/' 开头
                if link.attrs['href'].startswith("/"):
                    internal_links.append(include_url + link.attrs['href'])
                else:
                    internal_links.append(link.attrs['href'])
    # 返回包含所有内链的列表
    return internal_links


# 获取页面所有外链的列表
def get_external_links(bs_obj, exclude_url):
    external_links = []
    # 使用正则找出所有以"http"或"www"开头且不包含当前url的链接
    for link in bs_obj.findAll('a', href=re.compile("^(http|www)((?!" + exclude_url + ").)*$")):
        # 判断a标签的href是否为空
        if link.attrs['href'] is not None:
            # 如果当前链接不再集合中，就将其加入进去
            if link.attrs['href'] not in external_links:
                external_links.append(link.attrs['href'])
    # 返回包含所有外链的集合
    return external_links


# 拆分 url 地址为数组
def split_address(address):
    address_parts = address.replace("http://", "//").split("/")
    return address_parts


def get_random_external_link(starting_page):
    html = urlopen(starting_page)
    bs_obj = BeautifulSoup(html, features='lxml')
    # 调用获取所有外链集合的函数
    external_links = get_external_links(bs_obj, urlparse(starting_page).netloc)
    if len(external_links) == 0:
        print("未发现外链")
        domain = urlparse(starting_page).scheme + "://" + urlparse(starting_page).netloc
        # 调用获取所有内链集合的函数
        internal_links = get_internal_links(bs_obj, domain)
        # 递归调用
        return get_random_external_link(internal_links[random.randint(0, len(internal_links) - 1)])
    else:
        return external_links[random.randint(0, len(external_links) - 1)]


def follow_external_only(starting_site):
    external_link = get_random_external_link(starting_site)
    print("随机外链地址是{}".format(external_link))
    # 递归调用
    follow_external_only(external_link)


def get_all_external_links(site_url):
    html = urlopen(site_url)
    bs_obj = BeautifulSoup(html, features='lxml')
    # split_address(site_url)[0] 网站地址
    internal_links = get_internal_links(bs_obj, split_address(site_url)[0])
    external_links = get_external_links(bs_obj, split_address(site_url)[0])
    for link in external_links:
        if link not in all_ext_links:
            all_ext_links.add(link)
            print(link)
    for link in internal_links:
        if link not in all_int_links:
            print("即将获取链接的url是:" + link)
            all_int_links.add(link)
            # 递归调用
            get_all_external_links(link)


if __name__ == "__main__":
    get_all_external_links("https://www.nuist.edu.cn")
