package com.grimbo.chipped.data.client;

import com.grimbo.chipped.Chipped;
import com.grimbo.chipped.api.ChippedBlockType;
import com.grimbo.chipped.api.ChippedWoodType;
import com.grimbo.chipped.block.ChippedBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.DyeColor;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.List;

import static com.grimbo.chipped.block.ChippedBlockTypes.*;

import net.minecraftforge.registries.RegistryObject;

public class ChippedBlockStateProvider extends BlockStateProvider {

    public ChippedBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, Chipped.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        for (ChippedBlockType<Block> type : ChippedBlocks.stones18) {
            createCubeFromList(type);
        }

        createCubeFromList(
                STONE, COBBLESTONE, END_STONE, NETHERRACK, GILDED_BLACKSTONES, BLACKSTONES, BASALTS,
                OBSIDIAN, CRYING_OBSIDIAN, CLAYS, GLASSES, GLOWSTONES, SEA_LANTERNS, SHROOMLIGHTS,
                BROWN_MUSHROOM_BLOCK, RED_MUSHROOM_BLOCK, WARPED_WART_BLOCK, NETHER_WART_BLOCK, SOUL_SANDS
        );

        registerGlassPanes(GLASS_PANES, "glass", "glass_pane_1_top", 1, 6);
        registerGlassPanes(GLASS_PANES, "glass", "glass_pane_2_top", 7, 14);

        for (int id = 0; id < 16; id++) {
            DyeColor color = DyeColor.byId(id);
            createCubeFromList(TERRACOTTAS.get(color));
            createCubeFromList(CONCRETES.get(color));
            createCubeFromList(WOOL.get(color));
            createCubeFromList(STAINED_GLASSES.get(color));

            List<RegistryObject<WoolCarpetBlock>> blocks = CARPETS.get(color).getBlocks();
            for (int i = 0; i < blocks.size(); ++i) {
                int n = i + 1;
                simpleBlock(blocks.get(i).get(), models().carpet(color + "_carpet_" + n, modLoc("block/" + color + "_wool/" + color + "_wool_" + n)));
            }

            ChippedBlockType<StainedGlassPaneBlock> stainedGlassPanes = STAINED_GLASS_PANES.get(color);
            registerGlassPanes(
                    stainedGlassPanes,
                    color + "_stained_glass",
                    "glass_pane_2_top",
                    1,
                    stainedGlassPanes.getBlocks().size()
            );
        }

        for (ChippedWoodType wood : ChippedWoodType.VALUES) {
            createCubeFromList(PLANKS.get(wood));
            registerGlassPanes(GLASS_PANES, wood + "_wood_glass_pane", wood + "_wood_glass", "glass", wood + "_wood_glass_pane_top", 1, 6);
        }

        for (RegistryObject<HayBlock> block : HAY_BLOCKS.getBlocks()) {
            String name = block.getId().getPath();
            axisBlock(block.get(), modLoc("block/" + HAY_BLOCKS + "/" + name));
        }

        // Dried Kelp Block
        List<RegistryObject<Block>> dried_kelp_blocks = DRIED_KELP_BLOCKS.getBlocks();
        for (int i = 1; i <= 12; ++i) {
            if (i != 5) {
                RegistryObject<Block> block = dried_kelp_blocks.get(i - 1);
                String name = block.getId().getPath();
                if (i == 12) {
                    simpleBlock(block.get(), models().cubeAll(name, modLoc("block/" + DRIED_KELP_BLOCKS + "/" + name)));
                } else {
                    simpleBlock(block.get(), models().cubeBottomTop(name, modLoc("block/" + DRIED_KELP_BLOCKS + "/" + name + "_side"), modLoc("block/" + DRIED_KELP_BLOCKS + "/" + name + "_top"), modLoc("block/" + DRIED_KELP_BLOCKS + "/" + name + "_top")));
                }
            }
        }

        for (RegistryObject<MelonBlock> block : MELONS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cubeColumn(name, modLoc("block/" + MELONS + "/" + name + "_side"), modLoc("block/" + MELONS + "/" + name + "_top")));
        }

        // Pumpkins
        List<RegistryObject<PumpkinBlock>> pumpkin_blocks = PUMPKINS.getBlocks();
        for (int i = 1; i <= ChippedBlocks.specialPumpkinList.length; i++) {
            RegistryObject<PumpkinBlock> block = pumpkin_blocks.get(i - 1);
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cubeColumn(name, modLoc("block/" + PUMPKINS + "/" + name + "_side"), modLoc("block/" + PUMPKINS + "/" + name + "_top")));
        }

        // Rest are done manually
        for (int i = ChippedBlocks.specialPumpkinList.length + 1; i <= ChippedBlocks.specialPumpkinList.length + 3; i++) {
            RegistryObject<PumpkinBlock> block = pumpkin_blocks.get(i - 1);
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cubeBottomTop(name, modLoc("block/" + PUMPKINS + "/" + name + "_side"), modLoc("block/" + PUMPKINS + "/" + name + "_bottom"), modLoc("block/" + PUMPKINS + "/" + name + "_top")));
        }

        for (RegistryObject<MushroomBlock> block : BROWN_MUSHROOMS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + BROWN_MUSHROOMS + "/" + name)));
        }

        for (RegistryObject<MushroomBlock> block : RED_MUSHROOMS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + RED_MUSHROOMS + "/" + name)));
        }

        for (RegistryObject<MushroomBlock> block : WARPED_FUNGUS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + WARPED_FUNGUS + "/" + name)));
        }

        for (RegistryObject<MushroomBlock> block : CRIMSON_FUNGUS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + CRIMSON_FUNGUS + "/" + name)));
        }

        for (RegistryObject<RootsBlock> block : WARPED_ROOTS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + WARPED_ROOTS + "/" + name)));
        }

        for (RegistryObject<RootsBlock> block : CRIMSON_ROOTS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + CRIMSON_ROOTS + "/" + name)));
        }

        for (RegistryObject<NetherSproutsBlock> block : NETHER_SPROUTS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + NETHER_SPROUTS + "/" + name)));
        }

        for (RegistryObject<WebBlock> block : COBWEBS.getBlocks()) {
            String name = block.getId().getPath();
            simpleBlock(block.get(), models().cross(name, modLoc("block/" + COBWEBS + "/" + name)));
        }

        for (RegistryObject<Block> block : LANTERNS) {
            if (block.get() instanceof LanternBlock) {
                getVariantBuilder(block.get())
                        .partialState()
                        .with(LanternBlock.HANGING, false)
                        .modelForState()
                        .modelFile(models().getExistingFile(new ResourceLocation(Chipped.MOD_ID, "block/lanterns/" + block.getId().getPath())))
                        .addModel();
                getVariantBuilder(block.get())
                        .partialState()
                        .with(LanternBlock.HANGING, true)
                        .modelForState()
                        .modelFile(models().getExistingFile(new ResourceLocation(Chipped.MOD_ID, "block/lanterns/" + block.getId().getPath() + "_hanging")))
                        .addModel();
            }
        }

        for (RegistryObject<Block> block : SOUL_LANTERNS) {
            if (block.get() instanceof LanternBlock) {
                String name = block.getId().getPath();
                getVariantBuilder(block.get())
                        .partialState()
                        .with(LanternBlock.HANGING, false)
                        .modelForState()
                        .modelFile(models().getExistingFile(new ResourceLocation(Chipped.MOD_ID, "block/lanterns/" + name)))
                        .addModel();
                getVariantBuilder(block.get())
                        .partialState()
                        .with(LanternBlock.HANGING, true)
                        .modelForState()
                        .modelFile(models().getExistingFile(new ResourceLocation(Chipped.MOD_ID, "block/lanterns/" + name + "_hanging")))
                        .addModel();
            }
        }
        List<RegistryObject<RedstoneLampBlock>> redstoneLampBlocks = REDSTONE_LAMPS.getBlocks();
        for (int i = 1; i <= 18; i++) {
            Block lamp = redstoneLampBlocks.get(i - 1).get();
            getVariantBuilder(lamp)
                    .partialState()
                    .with(RedstoneLampBlock.LIT, false)
                    .modelForState()
                    .modelFile(models().cubeAll("redstone_lamp_" + i, modLoc("block/redstone_lamp/redstone_lamp_off_" + i)))
                    .addModel();
            getVariantBuilder(lamp)
                    .partialState()
                    .with(RedstoneLampBlock.LIT, true)
                    .modelForState()
                    .modelFile(models().cubeAll("redstone_lamp_on_" + i, modLoc("block/redstone_lamp/redstone_lamp_on_" + i)))
                    .addModel();
        }

        List<RegistryObject<TorchBlock>> torches = TORCHES.getBlocks();
        List<RegistryObject<WallTorchBlock>> wallTorches = ChippedBlocks.WALL_TORCHES;

        for (int i = 1; i <= 9; i++) {
            RegistryObject<TorchBlock> torchBlock = torches.get(i - 1);
            simpleBlock(torchBlock.get(), models().torch(torchBlock.getId().getPath(), modLoc("block/torch/torch_" + i)));
            Block wallTorchBlock = wallTorches.get(i - 1).get();
            for (Direction dir : RedstoneWallTorchBlock.FACING.getPossibleValues()) {
                int angle = Chipped.getTorchAngleFromDir(dir);
                getVariantBuilder(wallTorchBlock)
                        .partialState()
                        .with(RedstoneWallTorchBlock.FACING, dir)
                        .modelForState()
                        .modelFile(models().torchWall("wall_torch_" + i, modLoc("block/torch/torch_" + i)))
                        .rotationY(angle)
                        .addModel();
            }
        }

        List<RegistryObject<CarvedPumpkinBlock>> vanillaCarved = ChippedBlocks.VANILLA_CARVED_PUMPKINS;
        for (int i = 0; i < ChippedBlocks.carvedPumpkinList.length * 2; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                String carvedBlockName = vanillaCarved.get(i).getId().getPath();
                String carvedSubstring = carvedBlockName.substring(0, carvedBlockName.length() - (1 + (i / 2) >= 10 ? 2 : 1));
                String blockPath = "block/pumpkin/" + carvedSubstring + ChippedBlocks.carvedPumpkinList[(i / 2) % ChippedBlocks.carvedPumpkinList.length];
                getVariantBuilder(vanillaCarved.get(i).get())
                        .partialState()
                        .with(CarvedPumpkinBlock.FACING, direction)
                        .modelForState()
                        .modelFile(models().orientable(carvedBlockName, mcLoc("block/pumpkin_side"), modLoc(blockPath), mcLoc("block/pumpkin_top")))
                        .rotationY(Chipped.getAngleFromDir(direction))
                        .addModel();
            }
        }

        List<RegistryObject<CarvedPumpkinBlock>> specialCarved = ChippedBlocks.SPECIAL_CARVED_PUMPKINS;
        List<RegistryObject<PumpkinBlock>> specialPumpkins = PUMPKINS.getBlocks();

        for (int i = 0; i < ChippedBlocks.specialPumpkinList.length * 2; i += 2) {
            registerSpecialPumpkins(specialCarved, specialPumpkins, i);
        }

        for (int i = 1; i < ChippedBlocks.specialPumpkinList.length * 2; i += 2) {
            registerSpecialPumpkins(specialCarved, specialPumpkins, i);
        }

        registerRedstoneTorches();
        registerRedstoneTorchWall();
    }

    private <T extends Block> void createCubeFromList(ChippedBlockType<?>... blockLists) {
        for (ChippedBlockType<?> blockList : blockLists) {
            createCubeFromList(blockList);
        }
    }

    private <T extends Block> void createCubeFromList(ChippedBlockType<?> blockList) {
        for (RegistryObject<? extends Block> block : blockList) {
            simpleBlock(block.get(), models().cubeAll(block.getId().getPath(), modLoc("block/" + blockList + "/" + block.getId().getPath())));
        }
    }

    // We honestly can use varargs instead of start & end, though up to whether we actually will add more complex glass panes in the future
    private <T extends Block> void registerGlassPanes(ChippedBlockType<T> type, String originalType, String topName, int start, int end) {
        registerGlassPanes(type, type.getId().getPath(), originalType, null, topName, start, end);
    }

    // Unused
    private <T extends Block> void registerGlassPanes(ChippedBlockType<T> type, String originalType, String originalPath, String topName, int start, int end) {
        registerGlassPanes(type, type.getId().getPath(), originalType, originalPath, topName, start, end);
    }

    // Very janky, will try to attempt to pull from glass_pane/glass_pane_top for stained_glass_pane because of how the textures were designed, might need to rework depending on future textures
    private <T extends Block> void registerGlassPanes(ChippedBlockType<T> type, String glassPaneName, String originalType, String originalPath, String topName, int start, int end) {
        List<RegistryObject<T>> blocks = type.getBlocks().stream().filter(t -> t.getId().getPath().startsWith(glassPaneName)).toList();
        for (int i = start; i <= end; i++) {
            String block = glassPaneName + "_" + i;
            String originalBlock = originalType + "_" + i;
            if (originalPath == null) originalPath = originalType;
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().panePost(block + "_post", modLoc("block/" + originalPath + "/" + originalBlock), modLoc("block/glass_pane" + "/" + topName)))
                    .addModel();
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneSide(block + "_side", modLoc("block/" + originalPath + "/" + originalBlock), modLoc("block/glass_pane" + "/" + topName)))
                    .addModel()
                    .condition(CrossCollisionBlock.NORTH, true);
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneSide(block + "_side", modLoc("block/" + originalPath + "/" + originalBlock), modLoc("block/glass_pane" + "/" + topName)))
                    .rotationY(90)
                    .addModel()
                    .condition(CrossCollisionBlock.EAST, true);
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneSideAlt(block + "_side_alt", modLoc("block/" + originalPath + "/" + originalBlock), modLoc("block/glass_pane" + "/" + topName)))
                    .addModel()
                    .condition(CrossCollisionBlock.SOUTH, true);
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneSideAlt(block + "_side_alt", modLoc("block/" + originalPath + "/" + originalBlock), modLoc("block/" + originalPath + "/" + originalBlock)))
                    .rotationY(90)
                    .addModel()
                    .condition(CrossCollisionBlock.WEST, true);
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneNoSide(block + "_noside", modLoc("block/" + originalPath + "/" + originalBlock)))
                    .addModel()
                    .condition(CrossCollisionBlock.NORTH, false);
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneNoSideAlt(block + "_noside_alt", modLoc("block/" + originalPath + "/" + originalBlock)))
                    .addModel()
                    .condition(CrossCollisionBlock.EAST, false);
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneNoSideAlt(block + "_noside_alt", modLoc("block/" + originalPath + "/" + originalBlock)))
                    .rotationY(90)
                    .addModel()
                    .condition(CrossCollisionBlock.SOUTH, false);
            getMultipartBuilder(blocks.get(i - 1).get())
                    .part()
                    .modelFile(models().paneNoSide(block + "_noside", modLoc("block/" + originalPath + "/" + originalBlock)))
                    .rotationY(270)
                    .addModel()
                    .condition(CrossCollisionBlock.WEST, false);
        }
    }

    private void registerRedstoneTorches() {
        List<RegistryObject<RedstoneTorchBlock>> torches = REDSTONE_TORCHES.getBlocks();
        for (int i = 2; i <= torches.size() + 1; i++) {
            getVariantBuilder(torches.get(i - 2).get())
                    .partialState()
                    .with(RedstoneTorchBlock.LIT, false)
                    .modelForState()
                    .modelFile(models().torch("redstone_torch_" + i + "_off", modLoc("block/redstone_torch/redstone_torch_" + i + "_off")))
                    .addModel()
                    .partialState()
                    .with(RedstoneTorchBlock.LIT, true)
                    .modelForState()
                    .modelFile(models().torch("redstone_torch_" + i, modLoc("block/redstone_torch/redstone_torch_" + i)))
                    .addModel();
        }
    }

    private void registerRedstoneTorchWall() {
        List<RegistryObject<RedstoneWallTorchBlock>> torches = ChippedBlocks.REDSTONE_WALL_TORCHES;
        for (int i = 2; i <= torches.size() + 1; i++) {
            for (Direction dir : RedstoneWallTorchBlock.FACING.getPossibleValues()) {
                int angle = Chipped.getTorchAngleFromDir(dir);
                getVariantBuilder(torches.get(i - 2).get())
                        .partialState()
                        .with(RedstoneWallTorchBlock.FACING, dir)
                        .with(RedstoneTorchBlock.LIT, true)
                        .modelForState()
                        .modelFile(models().torchWall("redstone_wall_torch_" + i, modLoc("block/redstone_torch/redstone_torch_" + i)))
                        .rotationY(angle)
                        .addModel()
                        .partialState()
                        .with(RedstoneWallTorchBlock.FACING, dir)
                        .with(RedstoneWallTorchBlock.LIT, false)
                        .modelForState()
                        .modelFile(models().torchWall("redstone_wall_torch_" + i + "_off", modLoc("block/redstone_torch/redstone_torch_" + i + "_off")))
                        .rotationY(angle)
                        .addModel();
            }
        }
    }

    private void registerSpecialPumpkins(List<RegistryObject<CarvedPumpkinBlock>> specialCarved, List<RegistryObject<PumpkinBlock>> specialPumpkins, int index) {
        String carvedBlockName = specialCarved.get(index).getId().getPath();
        String pumpkinName = specialPumpkins.get(index / 2).getId().getPath();
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            // Carved Pumpkin
            getVariantBuilder(specialCarved.get(index).get())
                    .partialState()
                    .with(CarvedPumpkinBlock.FACING, direction)
                    .modelForState()
                    .modelFile(models().orientable(carvedBlockName, modLoc("block/pumpkin/" + pumpkinName + "_side"), modLoc("block/pumpkin/" + carvedBlockName), modLoc("block/pumpkin/" + pumpkinName + "_top")))
                    .rotationY(Chipped.getAngleFromDir(direction))
                    .addModel();
        }
    }
}
