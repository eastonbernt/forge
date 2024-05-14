package net.easton.tutorialmod.entity.client;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.easton.tutorialmod.entity.animations.ModAnimationDefinitions;
import net.easton.tutorialmod.entity.custom.PhoenixChickenEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class PhoenixChickenModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart phoenixchicken;
	//private final ModelPart head;

	public PhoenixChickenModel(ModelPart root) {
		this.phoenixchicken = root.getChild("phoenixchicken");
		//this.head = phoenixchicken.getChild("phoenixchicken").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition phoenixchicken = partdefinition.addOrReplaceChild("phoenixchicken", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = phoenixchicken.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 14).addBox(-2.0F, -12.0F, -8.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(24, 14).addBox(2.0F, -12.0F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 14).addBox(-3.0F, -12.0F, -4.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(17, 22).addBox(-3.0F, -13.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(2.0F, -13.0F, -3.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -10.0F, -10.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = phoenixchicken.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(8, 24).addBox(1.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-2.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 18).addBox(-2.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(14, 14).addBox(1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		//this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.PHOENIXCHICKEN_WALKING, limbSwing, limbSwingAmount, 2f, 2.5f);
		//this.animate(((PhoenixChickenEntity) entity).idleAnimationState, ModAnimationDefinitions.PHOENIXCHICKEN_IDLE, ageInTicks, 1f);
	}
	//private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		//pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		//pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		//this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		//this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	//}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		phoenixchicken.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return phoenixchicken;
	}
}