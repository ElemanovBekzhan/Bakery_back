package org.example.subd.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.subd.model.Ingredient;
import org.example.subd.model.IngredientMaterial;
import org.example.subd.model.Material;
import org.example.subd.model.Product;
import org.example.subd.model.dto.ingredients.IngredienUpdateDTO;
import org.example.subd.model.dto.ingredients.IngredientDTO;
import org.example.subd.model.dto.ingredients.IngredientResponseDTO;
import org.example.subd.model.dto.materials.MaterialResponseDTO;
import org.example.subd.model.mapper.IngredientMapper;
import org.example.subd.repository.IngredientMaterialRepo;
import org.example.subd.repository.IngredientRepo;
import org.example.subd.repository.MaterialRepo;
import org.example.subd.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class IngredientService {

    private final IngredientRepo ingredientRepo;
    private final IngredientMaterialRepo ingredientMaterialRepo;
    private final IngredientMapper ingredientMapper;
    private final ProductRepo productRepo;
    private final MaterialRepo materialRepo;

    public IngredientService(IngredientRepo ingredientRepo, IngredientMaterialRepo ingredientMaterialRepo, IngredientMapper ingredientMapper, ProductRepo productRepo, MaterialRepo materialRepo) {
        this.ingredientRepo = ingredientRepo;
        this.ingredientMaterialRepo = ingredientMaterialRepo;
        this.ingredientMapper = ingredientMapper;
        this.productRepo = productRepo;
        this.materialRepo = materialRepo;
    }


    @Transactional
    public IngredientDTO createIngredient(IngredientDTO dto) {
        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        List<Material> materials = materialRepo.findAllById(
                dto.getIngredientMaterials().stream()
                        .map(IngredientDTO.IngredientMaterialDto::getMaterialId)
                        .collect(Collectors.toList())
        );

        Ingredient i = ingredientMapper.toEntity(dto, product, materials);
        i = ingredientRepo.save(i);

        for(IngredientMaterial ingredientMaterial : i.getIngredientMaterials()){
            ingredientMaterialRepo.save(ingredientMaterial);
        }
        return ingredientMapper.toDto(i);
    }

    @Transactional
    public void updateIngredient(IngredienUpdateDTO dto) {


        /*Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));



        List<Material> materials = materialRepo.findAllById(dto.getIngredientMaterials()
                .stream()
                .map(IngredienUpdateDTO.IngredientMaterialDto::getMaterialId)
                .collect(Collectors.toList())
        );
        Ingredient i = ingredientMapper.toEntity(dto, product, materials);
        ingredientRepo.save(i);
        for(IngredientMaterial ingredientMaterial : i.getIngredientMaterials()){
            ingredientMaterialRepo.save(ingredientMaterial);
        }*/



        Ingredient ingredient = ingredientRepo.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Ingredient not found"));

        //Обновляем продукт
        ingredient.setProduct(productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found")));

        ingredient.getIngredientMaterials().clear();

        // Добавляем новые
        for (IngredienUpdateDTO.IngredientMaterialDto imDto : dto.getIngredientMaterials()) {
            Material material = materialRepo.findById(imDto.getMaterialId())
                    .orElseThrow(() -> new EntityNotFoundException("Material not found"));

            IngredientMaterial im = new IngredientMaterial();
            im.setIngredient(ingredient);
            im.setMaterial(material);
            im.setQuantity(imDto.getQuantity());
            im.setUnit(imDto.getUnit());
            ingredient.getIngredientMaterials().add(im);
        }

        // Сохраняем — каскад спасёт и новые, и удалит старые
        ingredientRepo.save(ingredient);
    }


    //Запрос списка ингредиентов
    public List<IngredientResponseDTO> getAll(){
        List<Ingredient> i = ingredientRepo.findAll();
        return i.stream().map(ingredient -> {
            IngredientResponseDTO dto = new IngredientResponseDTO();
            dto.setId(ingredient.getId());
            dto.setProductName(ingredient.getProduct().getName());


            List<MaterialResponseDTO> materialDtos = ingredient.getIngredientMaterials().stream().map(im ->{
                MaterialResponseDTO materialDto = new MaterialResponseDTO();
                materialDto.setMaterialName(im.getMaterial().getName());
                materialDto.setQuantity(im.getQuantity());
                materialDto.setUnit(im.getUnit());
                return materialDto;
            }).collect(Collectors.toList());

            dto.setMaterials(materialDtos);
            return dto;
        }).collect(Collectors.toList());
    }


    //Запрос одного ингредиента по айди
    public IngredientResponseDTO findById(UUID id) {
        Ingredient ingredient = ingredientRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ингредиент не найден" + id));
        IngredientResponseDTO dto = new IngredientResponseDTO();
        dto.setId(ingredient.getId());
        dto.setProductName(ingredient.getProduct().getName());
        List<MaterialResponseDTO> materialDtos = ingredient.getIngredientMaterials().stream()
                .map(im -> {
                    MaterialResponseDTO materialDto = new MaterialResponseDTO();
                    materialDto.setMaterialName(im.getMaterial().getName());
                    materialDto.setQuantity(im.getQuantity());
                    materialDto.setUnit(im.getUnit());
                    return materialDto;
                }).collect(Collectors.toList());
        dto.setMaterials(materialDtos);
        return dto;
    }




}
