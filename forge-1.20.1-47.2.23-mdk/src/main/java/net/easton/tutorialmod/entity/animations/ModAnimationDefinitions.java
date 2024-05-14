package net.easton.tutorialmod.entity.animations;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;

public class ModAnimationDefinitions {
    public static final AnimationDefinition PHOENIXCHICKEN_WALKING = AnimationDefinition.Builder.withLength(3f)
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.POSITION,
                            new Keyframe(0.08343333f, KeyframeAnimations.posVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.3433333f, KeyframeAnimations.posVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.4167667f, KeyframeAnimations.posVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.1676667f, KeyframeAnimations.posVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.CATMULLROM)))
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(5f, -2.71f, 2.27f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.20834334f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.625f, KeyframeAnimations.degreeVec(0f, -11.69f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.9167666f, KeyframeAnimations.degreeVec(0f, 0f, 0f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(2.4583435f, KeyframeAnimations.degreeVec(-4.06f, 3.32f, 0.72f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("body",
                    new AnimationChannel(AnimationChannel.Targets.SCALE,
                            new Keyframe(0.2916767f, KeyframeAnimations.scaleVec(1f, 1f, 1f),
                                    AnimationChannel.Interpolations.LINEAR),
                            new Keyframe(0.9583434f, KeyframeAnimations.scaleVec(1f, 1f, 1.1f),
                                    AnimationChannel.Interpolations.LINEAR)))
            .addAnimation("head",
                    new AnimationChannel(AnimationChannel.Targets.ROTATION,
                            new Keyframe(0f, KeyframeAnimations.degreeVec(2.36f, 2.37f, 11.36f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(0.16766666f, KeyframeAnimations.degreeVec(4.1f, 1.56f, 9.82f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.1676667f, KeyframeAnimations.degreeVec(4.95f, 33.97f, 12.48f),
                                    AnimationChannel.Interpolations.CATMULLROM),
                            new Keyframe(1.75f, KeyframeAnimations.degreeVec(0.73f, 0.31f, 0.41f),
                                    AnimationChannel.Interpolations.CATMULLROM))).build();
}
