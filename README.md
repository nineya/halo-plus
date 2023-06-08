<p align="center">
    <a href="https://docs.halo-plus.nineya.com" target="_blank" rel="noopener noreferrer">
        <img width="100" src="https://halo.run/logo" alt="Halo-Plus logo" />
    </a>
</p>

<p align="center"><b>Halo <b style="color: #097ff1">Plus</b></b> [ˈheɪloʊ plʌs]，一款现代化的开源博客/CMS系统，值得一试。</p>

<p align="center">
<a href="https://github.com/nineya/halo-plus/releases"><img alt="GitHub release" src="https://img.shields.io/github/release/nineya/halo-plus.svg?style=flat-square" /></a>
<a href="https://github.com/nineya/halo-plus/releases"><img alt="GitHub All Releases" src="https://img.shields.io/github/downloads/nineya/halo-plus/total.svg?style=flat-square" /></a>
<a href="https://hub.docker.com/r/nineya/halo-plus"><img alt="Docker pulls" src="https://img.shields.io/docker/pulls/nineya/halo-plus?style=flat-square" /></a>
<a href="https://github.com/nineya/halo-plus/commits"><img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/nineya/halo-plus.svg?style=flat-square" /></a>
<a href="https://github.com/nineya/halo-plus/actions"><img alt="GitHub Workflow Status" src="https://img.shields.io/github/workflow/status/nineya/halo-plus/Halo%20CI?style=flat-square" /></a>
<br />
<a href="https://halo.run">Halo官网</a>
<a href="https://bbs.halo.run">Halo官方社区</a>
<a href="https://docs.halo.nineya.com">Halo-Plus文档</a>
<a href="https://github.com/nineya">GitHub</a>
</p>

------------------------------

## 快速开始

### Fat Jar

下载最新的 Halo-Plus 运行包：

```bash
curl -L https://github.com/nineya/halo-plus/releases/download/v1.0.0/halo-plus-1.0.0.jar --output halo-plus.jar
```

更多更新内容，见微信公众号：

![玖涯菜菜子](https://blog.nineya.com/upload/2023/05/未标题-2.png)

### Docker

```bash
docker run -it -d --name halo-plus -p 8090:8090 -v ~/.halo:/root/.halo-plus --restart=always nineya/halo-plus:latest
```

详细部署文档请查阅：<https://docs.halo-plus.nineya.com/getting-started/install/linux>

## 生态

| 项目                                                                         | 状态                                                                                                                                                                             | 描述                                     |
| ---------------------------------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------- |
| [halo-admin](https://github.com/nineya/halo-puls-admin)                         | <a href="https://github.com/nineya/halo-admin/releases"><img alt="GitHub release" src="https://img.shields.io/github/release/nineya/halo-puls-admin.svg?style=flat-square" /></a> | Web 管理端 UI，已内置在主应用            |
| [halo-sdk-js](https://github.com/nineya/halo-sdk-js)                                 | <a href="https://github.com/nineya/halo-sdk-js"><img alt="npm release" src="https://img.shields.io/npm/v/@nineya/halo-content-api?style=flat-square"/></a>                             | JavaScript SDK                           |
| [halo-comment-dream](https://github.com/nineya/halo-comment-dream)                     | <a href="https://www.npmjs.com/package/halo-comment-dream"><img alt="npm release" src="https://img.shields.io/npm/v/halo-comment-dream?style=flat-square"/></a>                              | 独立评论组件，可以非常方便的集成到主题中 |
| [halo-theme-dream](https://github.com/nineya/halo-theme-dream)                        | <a href="https://github.com/nineya/halo-theme-dream/releases"><img alt="GitHub release" src="https://img.shields.io/github/release/nineya/halo-theme-dream.svg?style=flat-square" /></a> | Dream 博客主题            |

## 许可证

[![license](https://img.shields.io/github/license/nineya/halo-plus.svg?style=flat-square)](https://github.com/nineya/halo-plus/blob/master/LICENSE)

Halo-Plus 使用 GPL-v3.0 协议开源，请遵守开源协议。

## 贡献

参考 [CONTRIBUTING](https://github.com/nineya/halo-plus/blob/master/CONTRIBUTING.md)。

<a href="https://github.com/nineya/halo-plus/graphs/contributors"><img src="https://opencollective.com/halo/contributors.svg?width=890&button=false" /></a>

## 状态

![Repobeats analytics](https://repobeats.axiom.co/api/embed/cfab38e31f5e304787f87bae2bece907f850a8d6.svg "Repobeats analytics image")
