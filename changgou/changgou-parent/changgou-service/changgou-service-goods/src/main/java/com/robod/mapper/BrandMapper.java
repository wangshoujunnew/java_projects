package com.robod.mapper;

import com.robod.goods.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author Robod
 * @date 2020/7/2 9:09
 */
@Repository("brandMapper")
public interface BrandMapper extends Mapper<Brand> {


    /**
     * BrandMapper 需要在Application上被扫描 @MapperScan("com.robod.mapper")
     * tk.mybatis.mapper.common.Mapper支持以下几种类型的操作：
     * 
     * 查询操作：包括单实体查询、多实体查询、条件查询、排序查询、分页查询等。
     * 
     * 插入操作：支持单实体插入和批量插入。
     * 
     * 更新操作：支持单实体更新和批量更新。
     * 
     * 删除操作：支持根据主键删除、根据条件删除和批量删除。
     * 
     * 使用tk.mybatis.mapper.common.Mapper实现增删改查操作非常简单，只需要创建一个继承了该接口的Mapper接口，然后在XML文件中编写相应的SQL语句即可。
     */

    /**
     * 查询所有的品牌信息
     *
     * @return
     */
    @Select("select * from tb_brand")
    public List<Brand> findAll();

    /**
     * 根据id查询品牌信息
     *
     * @param id
     * @return
     */
    @Select("select * from tb_brand where id = #{id} limit 1")
    public Brand findById(Integer id);

    /**
     * 添加品牌
     *
     * @param brand
     */
    @Insert("insert into tb_brand values(#{brand.id},#{brand.name},#{brand.image},#{brand.letter},#{brand.seq})")
    public void add(@Param("brand") Brand brand);

    /**
     * 根据id修改品牌信息
     *
     * @param brand
     */
    @Insert("update tb_brand set name=#{brand.name},image=#{brand.image},letter=#{brand.letter},seq=#{brand.seq} where id=#{brand.id}")
    public void update(@Param("brand") Brand brand);

    /**
     * 根据id删除品牌
     *
     * @param id
     */
    @Delete("delete from tb_brand where id = #{id}")
    public void deleteBrand(Integer id);

    /**
     * 条件查询
     *
     * @param brand
     * @return
     */
    @SelectProvider(type = BrandMapperProvider.class, method = "findList")
    public List<Brand> findList(Brand brand);

    /**
     * 根据分类id查询对应的品牌集合
     * @param categoryId
     * @return
     * Dao层 BrandMapper.java
     */
    @Select("SELECT * FROM tb_brand WHERE id IN " +
            "(SELECT brand_id FROM tb_category_brand WHERE category_id=#{categoryId})")
    public List<Brand> findByCategory(int categoryId);

    class BrandMapperProvider {
        public String findList(Brand brand) {
            StringBuilder builder = new StringBuilder("select * from tb_brand where 0=0 ");
            if (brand != null) {
                if (!StringUtils.isEmpty(brand.getName())) {
                    builder.append(" and name like ").append("\"%").append(brand.getName()).append("%\" ");
                }
                if (!StringUtils.isEmpty(brand.getImage())) {
                    builder.append(" and image like ").append("\"%").append(brand.getImage()).append("%\" ");
                }
                if (!StringUtils.isEmpty(brand.getLetter())) {
                    builder.append(" and letter = ").append(" \"").append(brand.getLetter()).append("\" ");
                }
                if (brand.getSeq() != null) {
                    builder.append(" and seq = ").append(brand.getSeq());
                }
            }
            return builder.toString();
        }
    }

}
