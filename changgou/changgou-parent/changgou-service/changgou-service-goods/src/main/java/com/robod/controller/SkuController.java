package com.robod.controller;
import com.github.pagehelper.PageInfo;
import com.robod.entity.Result;
import com.robod.entity.StatusCode;
import com.robod.goods.pojo.Sku;
import com.robod.service.intf.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/****
 * @Author:admin
 * @Description:
 * @Date 2019/6/14 0:18
 *****/

@RestController
@RequestMapping("/sku")
@CrossOrigin
public class SkuController {

    @Autowired
    private SkuService skuService;

    /**
     * 根据sku_id集合查询sku集合
     * @param skuIds
     * @return
     */
    @PostMapping("/list/ids")
    public Result<List<Sku>> findBySkuIds(@RequestBody List<Long> skuIds) {
        List<Sku> skuList = skuService.findBySkuIds(skuIds);
        return new Result<>(true,StatusCode.OK,"查询成功",skuList);
    }

    /***
     * Sku分页条件搜索实现
     * @param sku
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false)  Sku sku, @PathVariable  int page, @PathVariable  int size){
        //调用SkuService实现分页条件查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(sku, page, size);
        return new Result(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * Sku分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用SkuService实现分页查询Sku
        PageInfo<Sku> pageInfo = skuService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCode.OK,"查询成功",pageInfo);
    }

    /***
     * 多条件搜索品牌数据
     * @param sku
     * @return
     */
    @PostMapping(value = "/search" )
    public Result<List<Sku>> findList(@RequestBody(required = false)  Sku sku){
        //调用SkuService实现条件查询Sku
        List<Sku> list = skuService.findList(sku);
        return new Result<List<Sku>>(true,StatusCode.OK,"查询成功",list);
    }

    /**
     * 根据spu_id查询对应的sku集合
     * @param id
     * @return
     */
    @GetMapping("/spu/{id}")
    public Result<List<Sku>> findBySpuId(@PathVariable Long id){
        List<Sku> list = skuService.findBySpuId(id);
        return new Result<>(true,StatusCode.OK,"查询成功",list);
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Long id){
        //调用SkuService实现根据主键删除
        skuService.delete(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /***
     * 修改Sku数据
     * @param sku
     * @param id
     * @return
     */
    @PutMapping(value="/{id}")
    public Result update(@RequestBody  Sku sku,@PathVariable Long id){
        //设置主键值
        sku.setId(id);
        //调用SkuService实现修改Sku
        skuService.update(sku);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 修改sku map集合
     * @param map
     * @return
     */
    @PutMapping(value="/update/map")
    public Result updateMap(@RequestBody Map<Long,Sku> map){
        //调用SkuService实现修改Sku
        skuService.updateMap(map);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /***
     * 新增Sku数据
     * @param sku
     * @return
     */
    @PostMapping
    public Result add(@RequestBody   Sku sku){
        //调用SkuService实现添加Sku
        skuService.add(sku);
        return new Result(true,StatusCode.OK,"添加成功");
    }


    /***
     * 根据ID查询Sku数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public Result<Sku> findById(@PathVariable Long id){
        //调用SkuService实现根据主键查询Sku
        Sku sku = skuService.findById(id);
        return new Result<Sku>(true,StatusCode.OK,"查询成功",sku);
    }

    /***
     * 查询Sku全部数据
     * @return
     */
    @GetMapping
    public Result<List<Sku>> findAll(){
        //调用SkuService实现查询所有Sku
        List<Sku> list = skuService.findAll();
        return new Result<List<Sku>>(true, StatusCode.OK,"查询成功",list) ;
    }

    /**
     * 根据spuid删除对应的sku
     * @param id
     * @return
     */
    @DeleteMapping("/deleteAll/{id}")
    public Result deleteAllSkuBySpuId(@PathVariable Long id){
        skuService.deleteAllSkuBySpuId(id);
        return new Result<List<Sku>>(true, StatusCode.OK,"删除成功") ;
    }
}
