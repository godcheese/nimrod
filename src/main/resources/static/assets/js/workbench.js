$(document).ready(function () {
    var option0 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎'],
            show: false
        },
        toolbox: {
            show: false,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'center',
                            max: 1548
                        }
                    }
                },
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        series: [
            {
                name: '访问来源',
                type: 'pie',
                radius: ['50%', '70%'],
                itemStyle: {
                    normal: {
                        label: {
                            show: false
                        },
                        labelLine: {
                            show: false
                        }
                    },
                    emphasis: {
                        label: {
                            show: true,
                            position: 'center',
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    }
                },
                data: [
                    {value: 335, name: '直接访问'},
                    {value: 310, name: '邮件营销'},
                    {value: 234, name: '联盟广告'},
                    {value: 135, name: '视频广告'},
                    {value: 1548, name: '搜索引擎'}
                ]
            }
        ]
    };

    var myChart0 = echarts.init(document.getElementById('chart0'));
    myChart0.setOption(option0);

    //chart1
    var option1 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎'],
            show: false
        },
        toolbox: {
            show: false,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['第一周', '第二周', '第三周', '第四周', '第五周', '第六周', '第七周']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '邮件营销',
                type: 'line',
                stack: '总量',
                data: [120, 132, 101, 134, 90, 230, 210]
            },
            {
                name: '联盟广告',
                type: 'line',
                stack: '总量',
                data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
                name: '视频广告',
                type: 'line',
                stack: '总量',
                data: [150, 232, 201, 154, 190, 330, 410]
            },
            {
                name: '直接访问',
                type: 'line',
                stack: '总量',
                data: [320, 332, 301, 334, 390, 330, 320]
            },
            {
                name: '搜索引擎',
                type: 'line',
                stack: '总量',
                data: [820, 932, 901, 934, 1290, 1330, 1320]
            }
        ]
    };
    var myChart1 = echarts.init(document.getElementById('chart1'));
    myChart1.setOption(option1);

    var option3 = {
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data: ['访问次数'],
            show: false
        },
        toolbox: {
            show: false,
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                data: ['采购组织', '供应商', '新物料', 'OA', '信息管理', '业务系统', '采购商']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '访问次数',
                type: 'bar',
                data: [60, 45, 73, 23, 37, 48, 18],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };

    var myChart3 = echarts.init(document.getElementById('chart3'));
    myChart3.setOption(option3);

    // 我的待办点击事件
    $(document).on('click', '.work-item.blue', function (event) {
        var width = (2 * $(this).width()) + 10;
        $(".todo-panel").width(width - 2).css({top: $(this).offset().top, left: $(this).offset().left}).show();
        event.stopPropagation();
    });
    $(".todo-panel").click(function () {
        event.stopPropagation();
    });
    $(document).click(function () {
        $(".todo-panel").hide();
    });

    // 文件下载 tab 事件处理
    $('.right-box-tab').find('a').click(function () {
        $(this).closest(".right-box-tab").find("a").removeClass("current");
        $(this).addClass("current");
        $($(this).attr('href')).closest(".right-box-tab-list").find("ul").addClass("hide");
        $($(this).attr('href')).removeClass("hide");
    });

});
