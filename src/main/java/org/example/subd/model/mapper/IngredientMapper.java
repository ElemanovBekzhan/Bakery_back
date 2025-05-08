package org.example.subd.model.mapper;


import org.example.subd.model.Ingredient;
import org.example.subd.model.IngredientMaterial;
import org.example.subd.model.Material;
import org.example.subd.model.Product;
import org.example.subd.model.dto.ingredients.IngredienUpdateDTO;
import org.example.subd.model.dto.ingredients.IngredientDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class IngredientMapper {

    public IngredientDTO toDto(Ingredient ingredient) {
        if (ingredient == null) return null;
        IngredientDTO dto = new IngredientDTO();
        dto.setProductId(ingredient.getProduct().getId());
        List<IngredientDTO.IngredientMaterialDto> materialDtos = ingredient.getIngredientMaterials()
                .stream()
                .map(this::materialToDto)
                .collect(Collectors.toList());
        dto.setIngredientMaterials(materialDtos);
        return dto;
    }


    public Ingredient toEntity(IngredienUpdateDTO dto, Product product, List<Material> materials){
        if (dto == null) return null;
        Ingredient i = new Ingredient();
        i.setId(dto.getId());
        i.setProduct(product);

        List<IngredientMaterial> ingredientMaterials = dto.getIngredientMaterials()
                .stream()
                .map(materialDto -> materialToEntityUp(materialDto, i, materials))
                .collect(Collectors.toList());
        i.setIngredientMaterials(ingredientMaterials);
        return i;
    }

    public Ingredient toEntity(IngredientDTO dto, Product product, List<Material> materials){
        if(dto == null) return null;
        Ingredient i = new Ingredient();
        i.setProduct(product);

        List<IngredientMaterial> ingredientMaterials = dto.getIngredientMaterials()
                .stream()
                .map(materialDto -> materialToEntity(materialDto, i, materials))
                .collect(Collectors.toList());

        i.setIngredientMaterials(ingredientMaterials);
        return i;
    }


    // Маппинг для материала
    public IngredientDTO.IngredientMaterialDto materialToDto(IngredientMaterial ingredientMaterial) {
        IngredientDTO.IngredientMaterialDto dto = new IngredientDTO.IngredientMaterialDto();
        dto.setMaterialId(ingredientMaterial.getMaterial().getId());
        dto.setQuantity(ingredientMaterial.getQuantity());
        dto.setUnit(ingredientMaterial.getUnit());
        return dto;
    }

    public IngredientMaterial materialToEntityUp(IngredienUpdateDTO.IngredientMaterialDto dto, Ingredient ingredient, List<Material> materials){
        IngredientMaterial ingredientMaterial = new IngredientMaterial();
        ingredientMaterial.setQuantity(dto.getQuantity());
        ingredientMaterial.setIngredient(ingredient);
        ingredientMaterial.setUnit(dto.getUnit());
        Material material = materials.stream()
                .filter(m -> m.getId().equals(dto.getMaterialId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid material id: " + dto.getMaterialId()));
        ingredientMaterial.setMaterial(material);
        return ingredientMaterial;
    }

    public IngredientMaterial materialToEntity(IngredientDTO.IngredientMaterialDto dto, Ingredient ingredient,
                                               List<Material> materials) {
        IngredientMaterial ingredientMaterial = new IngredientMaterial();
        ingredientMaterial.setQuantity(dto.getQuantity());
        ingredientMaterial.setIngredient(ingredient);
        ingredientMaterial.setUnit(dto.getUnit());
        Material material = materials.stream()
                .filter(m -> m.getId().equals(dto.getMaterialId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid material id: " + dto.getMaterialId()));
        ingredientMaterial.setMaterial(material);
        return ingredientMaterial;
    }
}
