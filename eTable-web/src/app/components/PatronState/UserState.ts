import { User } from 'src/app/domain/User';

  export declare interface UserState{
  
    setAuthentication(password : String , user : User) : void;
}
  