package com.hwangjr.soundhelper.pcm;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;

/**
 * this is a builder class for easily generating javax.sound.sampled.AudioFormat object.
 */
public class JavaAudioFormatBuilder {

    private enum Channel {
        MONO(1), STEREO(2), NOT_SPECIFIED(0);

        public int audioChannels;

        Channel(int i) {
            this.audioChannels = i;
        }
    }

    public static final AudioFormat PCM_SIGNED_8_KHZ_16_BIT_MONO_BIG_ENDIAN =
            new JavaAudioFormatBuilder().pcmSigned().sampleSizeInBits(16).sampleRate(8000.0f).mono().bigEndian().build();

    public static final AudioFormat PCM_SIGNED_8_KHZ_16_BIT_MONO_LITTLE_ENDIAN =
            new JavaAudioFormatBuilder().pcmSigned().sampleSizeInBits(16).sampleRate(8000.0f).mono().littleEndian().build();

    public static final AudioFormat PCM_SIGNED_44_KHZ_16_BIT_STEREO_LITTLE_ENDIAN =
            new JavaAudioFormatBuilder().pcmSigned().sampleSizeInBits(16).sampleRate(44100.0f).stereo().littleEndian().build();

    /**
     * The audio encoding technique used by this format.
     */
    private AudioFormat.Encoding encoding = AudioFormat.Encoding.PCM_SIGNED;

    /**
     * The number of samples played or recorded per second, for sounds that have this format.
     */
    private float sampleRate = 8000.0f;

    /**
     * The number of bits in each sample of a sound that has this format.
     */
    private int sampleSizeInBits = AudioSystem.NOT_SPECIFIED;

    /**
     * The number of audio channels in this format (1 for mono, 2 for stereo).
     */
    private Channel channel = Channel.NOT_SPECIFIED;

    /**
     * Indicates whether the audio data is stored in big-endian or little-endian order.
     */
    private boolean bigEndian = true;

    public JavaAudioFormatBuilder pcmSigned() {
        this.encoding = AudioFormat.Encoding.PCM_SIGNED;
        return this;
    }

    public JavaAudioFormatBuilder pcmUnsigned() {
        this.encoding = AudioFormat.Encoding.PCM_UNSIGNED;
        return this;
    }

    public JavaAudioFormatBuilder aLaw() {
        this.encoding = AudioFormat.Encoding.ALAW;
        return this;
    }

    public JavaAudioFormatBuilder uLaw() {
        this.encoding = AudioFormat.Encoding.ULAW;
        return this;
    }

    public JavaAudioFormatBuilder sampleRate(float sampleRate) {
        this.sampleRate = sampleRate;
        return this;
    }

    public JavaAudioFormatBuilder sampleSizeInBits(int sampleSizeInBits) {
        this.sampleSizeInBits = sampleSizeInBits;
        return this;
    }

    public JavaAudioFormatBuilder mono() {
        this.channel = Channel.MONO;
        return this;
    }

    public JavaAudioFormatBuilder stereo() {
        this.channel = Channel.STEREO;
        return this;
    }

    public JavaAudioFormatBuilder bigEndian() {
        this.bigEndian = true;
        return this;
    }

    public JavaAudioFormatBuilder littleEndian() {
        this.bigEndian = false;
        return this;
    }

    public AudioFormat build() {
        boolean signed = encoding.equals(AudioFormat.Encoding.PCM_SIGNED);
        return new AudioFormat(sampleRate, sampleSizeInBits, channel.audioChannels, signed, bigEndian);
    }


}
