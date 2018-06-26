/**
 * This class file was automatically generated by jASN1 v1.10.1-SNAPSHOT (http://www.openmuc.org)
 */

package org.openmuc.jasn1.compiler.various_tests;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import org.openmuc.jasn1.ber.*;
import org.openmuc.jasn1.ber.types.*;
import org.openmuc.jasn1.ber.types.string.*;


public class SequenceNameClashTest implements BerType, Serializable {

	private static final long serialVersionUID = 1L;

	public static class Myseqof implements BerType, Serializable {

		private static final long serialVersionUID = 1L;

		public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
		public byte[] code = null;
		public List<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger> seqOf = null;

		public Myseqof() {
			seqOf = new ArrayList<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger>();
		}

		public Myseqof(byte[] code) {
			this.code = code;
		}

		public Myseqof(List<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger> seqOf) {
			this.seqOf = seqOf;
		}

		public int encode(OutputStream reverseOS) throws IOException {
			return encode(reverseOS, true);
		}

		public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					reverseOS.write(code[i]);
				}
				if (withTag) {
					return tag.encode(reverseOS) + code.length;
				}
				return code.length;
			}

			int codeLength = 0;
			for (int i = (seqOf.size() - 1); i >= 0; i--) {
				codeLength += seqOf.get(i).encode(reverseOS, true);
			}

			codeLength += BerLength.encodeLength(reverseOS, codeLength);

			if (withTag) {
				codeLength += tag.encode(reverseOS);
			}

			return codeLength;
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, true);
		}

		public int decode(InputStream is, boolean withTag) throws IOException {
			int codeLength = 0;
			int subCodeLength = 0;
			BerTag berTag = new BerTag();
			if (withTag) {
				codeLength += tag.decodeAndCheck(is);
			}

			BerLength length = new BerLength();
			codeLength += length.decode(is);
			int totalLength = length.val;

			if (length.val == -1) {
				while (true) {
					subCodeLength += berTag.decode(is);

					if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
						int nextByte = is.read();
						if (nextByte != 0) {
							if (nextByte == -1) {
								throw new EOFException("Unexpected end of input stream.");
							}
							throw new IOException("Decoded sequence has wrong end of contents octets");
						}
						codeLength += subCodeLength + 1;
						return codeLength;
					}

					org.openmuc.jasn1.compiler.various_tests.UntaggedInteger element = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
					subCodeLength += element.decode(is, false);
					seqOf.add(element);
				}
			}
			while (subCodeLength < totalLength) {
				org.openmuc.jasn1.compiler.various_tests.UntaggedInteger element = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
				subCodeLength += element.decode(is, true);
				seqOf.add(element);
			}
			if (subCodeLength != totalLength) {
				throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

			}
			codeLength += subCodeLength;

			return codeLength;
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(reverseOS, false);
			code = reverseOS.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			sb.append("{\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			if (seqOf == null) {
				sb.append("null");
			}
			else {
				Iterator<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger> it = seqOf.iterator();
				if (it.hasNext()) {
					sb.append(it.next());
					while (it.hasNext()) {
						sb.append(",\n");
						for (int i = 0; i < indentLevel + 1; i++) {
							sb.append("\t");
						}
						sb.append(it.next());
					}
				}
			}

			sb.append("\n");
			for (int i = 0; i < indentLevel; i++) {
				sb.append("\t");
			}
			sb.append("}");
		}

	}

	public static class UntaggedInteger implements BerType, Serializable {

		private static final long serialVersionUID = 1L;

		public byte[] code = null;
		public org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger = null;
		public BerBoolean myBoolean = null;
		
		public UntaggedInteger() {
		}

		public UntaggedInteger(byte[] code) {
			this.code = code;
		}

		public UntaggedInteger(org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger, BerBoolean myBoolean) {
			this.myInteger = myInteger;
			this.myBoolean = myBoolean;
		}

		public int encode(OutputStream reverseOS) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					reverseOS.write(code[i]);
				}
				return code.length;
			}

			int codeLength = 0;
			int sublength;

			if (myBoolean != null) {
				sublength = myBoolean.encode(reverseOS, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 3
				reverseOS.write(0xA3);
				codeLength += 1;
				return codeLength;
			}
			
			if (myInteger != null) {
				sublength = myInteger.encode(reverseOS, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 2
				reverseOS.write(0xA2);
				codeLength += 1;
				return codeLength;
			}
			
			throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, null);
		}

		public int decode(InputStream is, BerTag berTag) throws IOException {

			int codeLength = 0;
			BerTag passedTag = berTag;

			if (berTag == null) {
				berTag = new BerTag();
				codeLength += berTag.decode(is);
			}

			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 2)) {
				codeLength += BerLength.skip(is);
				myInteger = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
				codeLength += myInteger.decode(is, true);
				return codeLength;
			}

			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 3)) {
				codeLength += BerLength.skip(is);
				myBoolean = new BerBoolean();
				codeLength += myBoolean.decode(is, true);
				return codeLength;
			}

			if (passedTag != null) {
				return 0;
			}

			throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(reverseOS);
			code = reverseOS.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			if (myInteger != null) {
				sb.append("myInteger: ").append(myInteger);
				return;
			}

			if (myBoolean != null) {
				sb.append("myBoolean: ").append(myBoolean);
				return;
			}

			sb.append("<none>");
		}

	}

	public static class MyChoice implements BerType, Serializable {

		private static final long serialVersionUID = 1L;

		public byte[] code = null;
		public static class MyChoice2 implements BerType, Serializable {

			private static final long serialVersionUID = 1L;

			public byte[] code = null;
			public org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger = null;
			public BerBoolean myBoolean = null;
			
			public MyChoice2() {
			}

			public MyChoice2(byte[] code) {
				this.code = code;
			}

			public MyChoice2(org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger, BerBoolean myBoolean) {
				this.myInteger = myInteger;
				this.myBoolean = myBoolean;
			}

			public int encode(OutputStream reverseOS) throws IOException {

				if (code != null) {
					for (int i = code.length - 1; i >= 0; i--) {
						reverseOS.write(code[i]);
					}
					return code.length;
				}

				int codeLength = 0;
				int sublength;

				if (myBoolean != null) {
					sublength = myBoolean.encode(reverseOS, true);
					codeLength += sublength;
					codeLength += BerLength.encodeLength(reverseOS, sublength);
					// write tag: CONTEXT_CLASS, CONSTRUCTED, 7
					reverseOS.write(0xA7);
					codeLength += 1;
					return codeLength;
				}
				
				if (myInteger != null) {
					sublength = myInteger.encode(reverseOS, true);
					codeLength += sublength;
					codeLength += BerLength.encodeLength(reverseOS, sublength);
					// write tag: CONTEXT_CLASS, CONSTRUCTED, 6
					reverseOS.write(0xA6);
					codeLength += 1;
					return codeLength;
				}
				
				throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
			}

			public int decode(InputStream is) throws IOException {
				return decode(is, null);
			}

			public int decode(InputStream is, BerTag berTag) throws IOException {

				int codeLength = 0;
				BerTag passedTag = berTag;

				if (berTag == null) {
					berTag = new BerTag();
					codeLength += berTag.decode(is);
				}

				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 6)) {
					codeLength += BerLength.skip(is);
					myInteger = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
					codeLength += myInteger.decode(is, true);
					return codeLength;
				}

				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 7)) {
					codeLength += BerLength.skip(is);
					myBoolean = new BerBoolean();
					codeLength += myBoolean.decode(is, true);
					return codeLength;
				}

				if (passedTag != null) {
					return 0;
				}

				throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
			}

			public void encodeAndSave(int encodingSizeGuess) throws IOException {
				ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
				encode(reverseOS);
				code = reverseOS.getArray();
			}

			public String toString() {
				StringBuilder sb = new StringBuilder();
				appendAsString(sb, 0);
				return sb.toString();
			}

			public void appendAsString(StringBuilder sb, int indentLevel) {

				if (myInteger != null) {
					sb.append("myInteger: ").append(myInteger);
					return;
				}

				if (myBoolean != null) {
					sb.append("myBoolean: ").append(myBoolean);
					return;
				}

				sb.append("<none>");
			}

		}

		public static class MySequence implements BerType, Serializable {

			private static final long serialVersionUID = 1L;

			public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);

			public byte[] code = null;
			public org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger = null;
			public BerBoolean myBoolean = null;
			
			public MySequence() {
			}

			public MySequence(byte[] code) {
				this.code = code;
			}

			public MySequence(org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger, BerBoolean myBoolean) {
				this.myInteger = myInteger;
				this.myBoolean = myBoolean;
			}

			public int encode(OutputStream reverseOS) throws IOException {
				return encode(reverseOS, true);
			}

			public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

				if (code != null) {
					for (int i = code.length - 1; i >= 0; i--) {
						reverseOS.write(code[i]);
					}
					if (withTag) {
						return tag.encode(reverseOS) + code.length;
					}
					return code.length;
				}

				int codeLength = 0;
				int sublength;

				sublength = myBoolean.encode(reverseOS, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 10
				reverseOS.write(0xAA);
				codeLength += 1;
				
				sublength = myInteger.encode(reverseOS, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 9
				reverseOS.write(0xA9);
				codeLength += 1;
				
				codeLength += BerLength.encodeLength(reverseOS, codeLength);

				if (withTag) {
					codeLength += tag.encode(reverseOS);
				}

				return codeLength;

			}

			public int decode(InputStream is) throws IOException {
				return decode(is, true);
			}

			public int decode(InputStream is, boolean withTag) throws IOException {
				int codeLength = 0;
				int subCodeLength = 0;
				BerTag berTag = new BerTag();

				if (withTag) {
					codeLength += tag.decodeAndCheck(is);
				}

				BerLength length = new BerLength();
				codeLength += length.decode(is);

				int totalLength = length.val;
				if (totalLength == -1) {
					subCodeLength += berTag.decode(is);

					if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
						int nextByte = is.read();
						if (nextByte != 0) {
							if (nextByte == -1) {
								throw new EOFException("Unexpected end of input stream.");
							}
							throw new IOException("Decoded sequence has wrong end of contents octets");
						}
						codeLength += subCodeLength + 1;
						return codeLength;
					}
					if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 9)) {
						codeLength += length.decode(is);
						myInteger = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
						subCodeLength += myInteger.decode(is, true);
						subCodeLength += berTag.decode(is);
					}
					if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
						int nextByte = is.read();
						if (nextByte != 0) {
							if (nextByte == -1) {
								throw new EOFException("Unexpected end of input stream.");
							}
							throw new IOException("Decoded sequence has wrong end of contents octets");
						}
						codeLength += subCodeLength + 1;
						return codeLength;
					}
					if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 10)) {
						codeLength += length.decode(is);
						myBoolean = new BerBoolean();
						subCodeLength += myBoolean.decode(is, true);
						subCodeLength += berTag.decode(is);
					}
					int nextByte = is.read();
					if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
					|| nextByte != 0) {
						if (nextByte == -1) {
							throw new EOFException("Unexpected end of input stream.");
						}
						throw new IOException("Decoded sequence has wrong end of contents octets");
					}
					codeLength += subCodeLength + 1;
					return codeLength;
				}

				codeLength += totalLength;

				subCodeLength += berTag.decode(is);
				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 9)) {
					subCodeLength += length.decode(is);
					myInteger = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
					subCodeLength += myInteger.decode(is, true);
					subCodeLength += berTag.decode(is);
				}
				else {
					throw new IOException("Tag does not match the mandatory sequence element tag.");
				}
				
				if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 10)) {
					subCodeLength += length.decode(is);
					myBoolean = new BerBoolean();
					subCodeLength += myBoolean.decode(is, true);
					if (subCodeLength == totalLength) {
						return codeLength;
					}
				}
				throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

				
			}

			public void encodeAndSave(int encodingSizeGuess) throws IOException {
				ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
				encode(reverseOS, false);
				code = reverseOS.getArray();
			}

			public String toString() {
				StringBuilder sb = new StringBuilder();
				appendAsString(sb, 0);
				return sb.toString();
			}

			public void appendAsString(StringBuilder sb, int indentLevel) {

				sb.append("{");
				sb.append("\n");
				for (int i = 0; i < indentLevel + 1; i++) {
					sb.append("\t");
				}
				if (myInteger != null) {
					sb.append("myInteger: ").append(myInteger);
				}
				else {
					sb.append("myInteger: <empty-required-field>");
				}
				
				sb.append(",\n");
				for (int i = 0; i < indentLevel + 1; i++) {
					sb.append("\t");
				}
				if (myBoolean != null) {
					sb.append("myBoolean: ").append(myBoolean);
				}
				else {
					sb.append("myBoolean: <empty-required-field>");
				}
				
				sb.append("\n");
				for (int i = 0; i < indentLevel; i++) {
					sb.append("\t");
				}
				sb.append("}");
			}

		}

		public static class Myseqof implements BerType, Serializable {

			private static final long serialVersionUID = 1L;

			public static final BerTag tag = new BerTag(BerTag.UNIVERSAL_CLASS, BerTag.CONSTRUCTED, 16);
			public byte[] code = null;
			public List<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger> seqOf = null;

			public Myseqof() {
				seqOf = new ArrayList<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger>();
			}

			public Myseqof(byte[] code) {
				this.code = code;
			}

			public Myseqof(List<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger> seqOf) {
				this.seqOf = seqOf;
			}

			public int encode(OutputStream reverseOS) throws IOException {
				return encode(reverseOS, true);
			}

			public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

				if (code != null) {
					for (int i = code.length - 1; i >= 0; i--) {
						reverseOS.write(code[i]);
					}
					if (withTag) {
						return tag.encode(reverseOS) + code.length;
					}
					return code.length;
				}

				int codeLength = 0;
				for (int i = (seqOf.size() - 1); i >= 0; i--) {
					codeLength += seqOf.get(i).encode(reverseOS, true);
				}

				codeLength += BerLength.encodeLength(reverseOS, codeLength);

				if (withTag) {
					codeLength += tag.encode(reverseOS);
				}

				return codeLength;
			}

			public int decode(InputStream is) throws IOException {
				return decode(is, true);
			}

			public int decode(InputStream is, boolean withTag) throws IOException {
				int codeLength = 0;
				int subCodeLength = 0;
				BerTag berTag = new BerTag();
				if (withTag) {
					codeLength += tag.decodeAndCheck(is);
				}

				BerLength length = new BerLength();
				codeLength += length.decode(is);
				int totalLength = length.val;

				if (length.val == -1) {
					while (true) {
						subCodeLength += berTag.decode(is);

						if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
							int nextByte = is.read();
							if (nextByte != 0) {
								if (nextByte == -1) {
									throw new EOFException("Unexpected end of input stream.");
								}
								throw new IOException("Decoded sequence has wrong end of contents octets");
							}
							codeLength += subCodeLength + 1;
							return codeLength;
						}

						org.openmuc.jasn1.compiler.various_tests.UntaggedInteger element = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
						subCodeLength += element.decode(is, false);
						seqOf.add(element);
					}
				}
				while (subCodeLength < totalLength) {
					org.openmuc.jasn1.compiler.various_tests.UntaggedInteger element = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
					subCodeLength += element.decode(is, true);
					seqOf.add(element);
				}
				if (subCodeLength != totalLength) {
					throw new IOException("Decoded SequenceOf or SetOf has wrong length. Expected " + totalLength + " but has " + subCodeLength);

				}
				codeLength += subCodeLength;

				return codeLength;
			}

			public void encodeAndSave(int encodingSizeGuess) throws IOException {
				ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
				encode(reverseOS, false);
				code = reverseOS.getArray();
			}

			public String toString() {
				StringBuilder sb = new StringBuilder();
				appendAsString(sb, 0);
				return sb.toString();
			}

			public void appendAsString(StringBuilder sb, int indentLevel) {

				sb.append("{\n");
				for (int i = 0; i < indentLevel + 1; i++) {
					sb.append("\t");
				}
				if (seqOf == null) {
					sb.append("null");
				}
				else {
					Iterator<org.openmuc.jasn1.compiler.various_tests.UntaggedInteger> it = seqOf.iterator();
					if (it.hasNext()) {
						sb.append(it.next());
						while (it.hasNext()) {
							sb.append(",\n");
							for (int i = 0; i < indentLevel + 1; i++) {
								sb.append("\t");
							}
							sb.append(it.next());
						}
					}
				}

				sb.append("\n");
				for (int i = 0; i < indentLevel; i++) {
					sb.append("\t");
				}
				sb.append("}");
			}

		}

		public org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger = null;
		public MyChoice2 myChoice2 = null;
		public MySequence mySequence = null;
		public Myseqof myseqof = null;
		
		public MyChoice() {
		}

		public MyChoice(byte[] code) {
			this.code = code;
		}

		public MyChoice(org.openmuc.jasn1.compiler.various_tests.UntaggedInteger myInteger, MyChoice2 myChoice2, MySequence mySequence, Myseqof myseqof) {
			this.myInteger = myInteger;
			this.myChoice2 = myChoice2;
			this.mySequence = mySequence;
			this.myseqof = myseqof;
		}

		public int encode(OutputStream reverseOS) throws IOException {

			if (code != null) {
				for (int i = code.length - 1; i >= 0; i--) {
					reverseOS.write(code[i]);
				}
				return code.length;
			}

			int codeLength = 0;
			int sublength;

			if (myseqof != null) {
				sublength = myseqof.encode(reverseOS, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 1
				reverseOS.write(0xA1);
				codeLength += 1;
				return codeLength;
			}
			
			if (mySequence != null) {
				sublength = mySequence.encode(reverseOS, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 8
				reverseOS.write(0xA8);
				codeLength += 1;
				return codeLength;
			}
			
			if (myChoice2 != null) {
				sublength = myChoice2.encode(reverseOS);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 5
				reverseOS.write(0xA5);
				codeLength += 1;
				return codeLength;
			}
			
			if (myInteger != null) {
				sublength = myInteger.encode(reverseOS, true);
				codeLength += sublength;
				codeLength += BerLength.encodeLength(reverseOS, sublength);
				// write tag: CONTEXT_CLASS, CONSTRUCTED, 4
				reverseOS.write(0xA4);
				codeLength += 1;
				return codeLength;
			}
			
			throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
		}

		public int decode(InputStream is) throws IOException {
			return decode(is, null);
		}

		public int decode(InputStream is, BerTag berTag) throws IOException {

			int codeLength = 0;
			BerTag passedTag = berTag;

			if (berTag == null) {
				berTag = new BerTag();
				codeLength += berTag.decode(is);
			}

			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 4)) {
				codeLength += BerLength.skip(is);
				myInteger = new org.openmuc.jasn1.compiler.various_tests.UntaggedInteger();
				codeLength += myInteger.decode(is, true);
				return codeLength;
			}

			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 5)) {
				codeLength += BerLength.skip(is);
				myChoice2 = new MyChoice2();
				codeLength += myChoice2.decode(is, null);
				return codeLength;
			}

			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 8)) {
				codeLength += BerLength.skip(is);
				mySequence = new MySequence();
				codeLength += mySequence.decode(is, true);
				return codeLength;
			}

			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
				codeLength += BerLength.skip(is);
				myseqof = new Myseqof();
				codeLength += myseqof.decode(is, true);
				return codeLength;
			}

			if (passedTag != null) {
				return 0;
			}

			throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(reverseOS);
			code = reverseOS.getArray();
		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			if (myInteger != null) {
				sb.append("myInteger: ").append(myInteger);
				return;
			}

			if (myChoice2 != null) {
				sb.append("myChoice2: ");
				myChoice2.appendAsString(sb, indentLevel + 1);
				return;
			}

			if (mySequence != null) {
				sb.append("mySequence: ");
				mySequence.appendAsString(sb, indentLevel + 1);
				return;
			}

			if (myseqof != null) {
				sb.append("myseqof: ");
				myseqof.appendAsString(sb, indentLevel + 1);
				return;
			}

			sb.append("<none>");
		}

	}

	public static final BerTag tag = new BerTag(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 43);

	public byte[] code = null;
	public Myseqof myseqof = null;
	public UntaggedInteger untaggedInteger = null;
	public MyChoice myChoice = null;
	
	public SequenceNameClashTest() {
	}

	public SequenceNameClashTest(byte[] code) {
		this.code = code;
	}

	public SequenceNameClashTest(Myseqof myseqof, UntaggedInteger untaggedInteger, MyChoice myChoice) {
		this.myseqof = myseqof;
		this.untaggedInteger = untaggedInteger;
		this.myChoice = myChoice;
	}

	public int encode(OutputStream reverseOS) throws IOException {
		return encode(reverseOS, true);
	}

	public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

		if (code != null) {
			for (int i = code.length - 1; i >= 0; i--) {
				reverseOS.write(code[i]);
			}
			if (withTag) {
				return tag.encode(reverseOS) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		int sublength;

		codeLength += myChoice.encode(reverseOS);
		
		if (untaggedInteger != null) {
			codeLength += untaggedInteger.encode(reverseOS);
		}
		
		sublength = myseqof.encode(reverseOS, true);
		codeLength += sublength;
		codeLength += BerLength.encodeLength(reverseOS, sublength);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 1
		reverseOS.write(0xA1);
		codeLength += 1;
		
		codeLength += BerLength.encodeLength(reverseOS, codeLength);
		reverseOS.write(0x30);
		codeLength++;

		codeLength += BerLength.encodeLength(reverseOS, codeLength);

		if (withTag) {
			codeLength += tag.encode(reverseOS);
		}

		return codeLength;

	}

	public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int codeLength = 0;
		int subCodeLength = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			codeLength += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		codeLength += length.decode(is);

		int totalLength = length.val;
		if (totalLength == -1) {
			subCodeLength += berTag.decode(is);

			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
				codeLength += length.decode(is);
				myseqof = new Myseqof();
				subCodeLength += myseqof.decode(is, true);
				subCodeLength += berTag.decode(is);
			}
			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			untaggedInteger = new UntaggedInteger();
			int choiceDecodeLength = untaggedInteger.decode(is, berTag);
			if (choiceDecodeLength != 0) {
				subCodeLength += choiceDecodeLength;
				subCodeLength += berTag.decode(is);
			}
			else {
				untaggedInteger = null;
			}

			if (berTag.tagNumber == 0 && berTag.tagClass == 0 && berTag.primitive == 0) {
				int nextByte = is.read();
				if (nextByte != 0) {
					if (nextByte == -1) {
						throw new EOFException("Unexpected end of input stream.");
					}
					throw new IOException("Decoded sequence has wrong end of contents octets");
				}
				codeLength += subCodeLength + 1;
				return codeLength;
			}
			myChoice = new MyChoice();
			choiceDecodeLength = myChoice.decode(is, berTag);
			if (choiceDecodeLength != 0) {
				subCodeLength += choiceDecodeLength;
				subCodeLength += berTag.decode(is);
			}
			else {
				myChoice = null;
			}

			int nextByte = is.read();
			if (berTag.tagNumber != 0 || berTag.tagClass != 0 || berTag.primitive != 0
			|| nextByte != 0) {
				if (nextByte == -1) {
					throw new EOFException("Unexpected end of input stream.");
				}
				throw new IOException("Decoded sequence has wrong end of contents octets");
			}
			codeLength += subCodeLength + 1;
			return codeLength;
		}

		codeLength += totalLength;

		int nextByte = is.read();
		if (nextByte == -1) {
			throw new EOFException("Unexpected end of input stream.");
		}
		if (nextByte != (0x30)) {
			throw new IOException("Tag does not match!");
		}
		length.decode(is);
		totalLength = length.val;

		subCodeLength += berTag.decode(is);
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 1)) {
			subCodeLength += length.decode(is);
			myseqof = new Myseqof();
			subCodeLength += myseqof.decode(is, true);
			subCodeLength += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match the mandatory sequence element tag.");
		}
		
		untaggedInteger = new UntaggedInteger();
		int choiceDecodeLength = untaggedInteger.decode(is, berTag);
		if (choiceDecodeLength != 0) {
			subCodeLength += choiceDecodeLength;
			subCodeLength += berTag.decode(is);
		}
		else {
			untaggedInteger = null;
		}
		
		myChoice = new MyChoice();
		subCodeLength += myChoice.decode(is, berTag);
		if (subCodeLength == totalLength) {
			return codeLength;
		}
		throw new IOException("Unexpected end of sequence, length tag: " + totalLength + ", actual sequence length: " + subCodeLength);

		
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS, false);
		code = reverseOS.getArray();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (myseqof != null) {
			sb.append("myseqof: ");
			myseqof.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("myseqof: <empty-required-field>");
		}
		
		if (untaggedInteger != null) {
			sb.append(",\n");
			for (int i = 0; i < indentLevel + 1; i++) {
				sb.append("\t");
			}
			sb.append("untaggedInteger: ");
			untaggedInteger.appendAsString(sb, indentLevel + 1);
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (myChoice != null) {
			sb.append("myChoice: ");
			myChoice.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("myChoice: <empty-required-field>");
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

