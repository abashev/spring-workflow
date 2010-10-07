// $ANTLR 3.0.1 RoleExpression.g 2008-04-01 15:36:30

package org.springframework.extensions.workflow.instance.permissions.evaluator;


import org.antlr.runtime.*;

import java.io.Serializable;

public class RoleExpressionLexer extends Lexer implements Serializable{
    public static final int AND=11;
    public static final int AND_DEF=8;
    public static final int OR_DEF=7;
    public static final int EXPR_DEF=4;
    public static final int EOF=-1;
    public static final int WS=15;
    public static final int NOT_DEF=5;
    public static final int T17=17;
    public static final int Tokens=18;
    public static final int IDPART=13;
    public static final int T16=16;
    public static final int ROLE_DEF=6;
    public static final int NOT=10;
    public static final int OR=12;
    public static final int BID=14;
    public static final int ID=9;
    public RoleExpressionLexer() {;} 
    public RoleExpressionLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "RoleExpression.g"; }

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // RoleExpression.g:6:5: ( '(' )
            // RoleExpression.g:6:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // RoleExpression.g:7:5: ( ')' )
            // RoleExpression.g:7:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start OR
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            // RoleExpression.g:35:6: ( '||' )
            // RoleExpression.g:35:8: '||'
            {
            match("||"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR

    // $ANTLR start AND
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            // RoleExpression.g:36:5: ( '&&' )
            // RoleExpression.g:36:7: '&&'
            {
            match("&&"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AND

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // RoleExpression.g:37:5: ( '!' )
            // RoleExpression.g:37:7: '!'
            {
            match('!'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start ID
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            // RoleExpression.g:38:4: ( IDPART | BID )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0>='A' && LA1_0<='Z')||LA1_0=='_'||(LA1_0>='a' && LA1_0<='z')) ) {
                alt1=1;
            }
            else if ( (LA1_0=='[') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("38:1: ID : ( IDPART | BID );", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // RoleExpression.g:38:6: IDPART
                    {
                    mIDPART(); 

                    }
                    break;
                case 2 :
                    // RoleExpression.g:38:15: BID
                    {
                    mBID(); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ID

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // RoleExpression.g:39:6: ( ( ' ' | '\\t' )+ )
            // RoleExpression.g:39:11: ( ' ' | '\\t' )+
            {
            // RoleExpression.g:39:11: ( ' ' | '\\t' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0=='\t'||LA2_0==' ') ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // RoleExpression.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            skip();

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    // $ANTLR start IDPART
    public final void mIDPART() throws RecognitionException {
        try {
            // RoleExpression.g:42:9: ( ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+ ( '0' .. '9' )* )
            // RoleExpression.g:42:17: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+ ( '0' .. '9' )*
            {
            // RoleExpression.g:42:17: ( 'a' .. 'z' | 'A' .. 'Z' | '_' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='A' && LA3_0<='Z')||LA3_0=='_'||(LA3_0>='a' && LA3_0<='z')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // RoleExpression.g:
            	    {
            	    if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            // RoleExpression.g:42:42: ( '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // RoleExpression.g:42:43: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end IDPART

    // $ANTLR start BID
    public final void mBID() throws RecognitionException {
        try {
            // RoleExpression.g:44:5: ( '[' (~ ']' )* ']' )
            // RoleExpression.g:44:9: '[' (~ ']' )* ']'
            {
            match('['); 
            // RoleExpression.g:44:13: (~ ']' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='\\')||(LA5_0>='^' && LA5_0<='\uFFFE')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // RoleExpression.g:44:15: ~ ']'
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\\')||(input.LA(1)>='^' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            match(']'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end BID

    public void mTokens() throws RecognitionException {
        // RoleExpression.g:1:8: ( T16 | T17 | OR | AND | NOT | ID | WS )
        int alt6=7;
        switch ( input.LA(1) ) {
        case '(':
            {
            alt6=1;
            }
            break;
        case ')':
            {
            alt6=2;
            }
            break;
        case '|':
            {
            alt6=3;
            }
            break;
        case '&':
            {
            alt6=4;
            }
            break;
        case '!':
            {
            alt6=5;
            }
            break;
        case 'A':
        case 'B':
        case 'C':
        case 'D':
        case 'E':
        case 'F':
        case 'G':
        case 'H':
        case 'I':
        case 'J':
        case 'K':
        case 'L':
        case 'M':
        case 'N':
        case 'O':
        case 'P':
        case 'Q':
        case 'R':
        case 'S':
        case 'T':
        case 'U':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '[':
        case '_':
        case 'a':
        case 'b':
        case 'c':
        case 'd':
        case 'e':
        case 'f':
        case 'g':
        case 'h':
        case 'i':
        case 'j':
        case 'k':
        case 'l':
        case 'm':
        case 'n':
        case 'o':
        case 'p':
        case 'q':
        case 'r':
        case 's':
        case 't':
        case 'u':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt6=6;
            }
            break;
        case '\t':
        case ' ':
            {
            alt6=7;
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T16 | T17 | OR | AND | NOT | ID | WS );", 6, 0, input);

            throw nvae;
        }

        switch (alt6) {
            case 1 :
                // RoleExpression.g:1:10: T16
                {
                mT16(); 

                }
                break;
            case 2 :
                // RoleExpression.g:1:14: T17
                {
                mT17(); 

                }
                break;
            case 3 :
                // RoleExpression.g:1:18: OR
                {
                mOR(); 

                }
                break;
            case 4 :
                // RoleExpression.g:1:21: AND
                {
                mAND(); 

                }
                break;
            case 5 :
                // RoleExpression.g:1:25: NOT
                {
                mNOT(); 

                }
                break;
            case 6 :
                // RoleExpression.g:1:29: ID
                {
                mID(); 

                }
                break;
            case 7 :
                // RoleExpression.g:1:32: WS
                {
                mWS(); 

                }
                break;

        }

    }


 

}