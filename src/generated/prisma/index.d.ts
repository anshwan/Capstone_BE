
/**
 * Client
**/

import * as runtime from './runtime/library.js';
import $Types = runtime.Types // general types
import $Public = runtime.Types.Public
import $Utils = runtime.Types.Utils
import $Extensions = runtime.Types.Extensions
import $Result = runtime.Types.Result

export type PrismaPromise<T> = $Public.PrismaPromise<T>


/**
 * Model User
 * 
 */
export type User = $Result.DefaultSelection<Prisma.$UserPayload>
/**
 * Model Model
 * 
 */
export type Model = $Result.DefaultSelection<Prisma.$ModelPayload>
/**
 * Model License
 * 
 */
export type License = $Result.DefaultSelection<Prisma.$LicensePayload>

/**
 * ##  Prisma Client ʲˢ
 *
 * Type-safe database client for TypeScript & Node.js
 * @example
 * ```
 * const prisma = new PrismaClient()
 * // Fetch zero or more Users
 * const users = await prisma.user.findMany()
 * ```
 *
 *
 * Read more in our [docs](https://www.prisma.io/docs/reference/tools-and-interfaces/prisma-client).
 */
export class PrismaClient<
  ClientOptions extends Prisma.PrismaClientOptions = Prisma.PrismaClientOptions,
  U = 'log' extends keyof ClientOptions ? ClientOptions['log'] extends Array<Prisma.LogLevel | Prisma.LogDefinition> ? Prisma.GetEvents<ClientOptions['log']> : never : never,
  ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs
> {
  [K: symbol]: { types: Prisma.TypeMap<ExtArgs>['other'] }

    /**
   * ##  Prisma Client ʲˢ
   *
   * Type-safe database client for TypeScript & Node.js
   * @example
   * ```
   * const prisma = new PrismaClient()
   * // Fetch zero or more Users
   * const users = await prisma.user.findMany()
   * ```
   *
   *
   * Read more in our [docs](https://www.prisma.io/docs/reference/tools-and-interfaces/prisma-client).
   */

  constructor(optionsArg ?: Prisma.Subset<ClientOptions, Prisma.PrismaClientOptions>);
  $on<V extends U>(eventType: V, callback: (event: V extends 'query' ? Prisma.QueryEvent : Prisma.LogEvent) => void): PrismaClient;

  /**
   * Connect with the database
   */
  $connect(): $Utils.JsPromise<void>;

  /**
   * Disconnect from the database
   */
  $disconnect(): $Utils.JsPromise<void>;

  /**
   * Add a middleware
   * @deprecated since 4.16.0. For new code, prefer client extensions instead.
   * @see https://pris.ly/d/extensions
   */
  $use(cb: Prisma.Middleware): void

/**
   * Executes a prepared raw query and returns the number of affected rows.
   * @example
   * ```
   * const result = await prisma.$executeRaw`UPDATE User SET cool = ${true} WHERE email = ${'user@email.com'};`
   * ```
   *
   * Read more in our [docs](https://www.prisma.io/docs/reference/tools-and-interfaces/prisma-client/raw-database-access).
   */
  $executeRaw<T = unknown>(query: TemplateStringsArray | Prisma.Sql, ...values: any[]): Prisma.PrismaPromise<number>;

  /**
   * Executes a raw query and returns the number of affected rows.
   * Susceptible to SQL injections, see documentation.
   * @example
   * ```
   * const result = await prisma.$executeRawUnsafe('UPDATE User SET cool = $1 WHERE email = $2 ;', true, 'user@email.com')
   * ```
   *
   * Read more in our [docs](https://www.prisma.io/docs/reference/tools-and-interfaces/prisma-client/raw-database-access).
   */
  $executeRawUnsafe<T = unknown>(query: string, ...values: any[]): Prisma.PrismaPromise<number>;

  /**
   * Performs a prepared raw query and returns the `SELECT` data.
   * @example
   * ```
   * const result = await prisma.$queryRaw`SELECT * FROM User WHERE id = ${1} OR email = ${'user@email.com'};`
   * ```
   *
   * Read more in our [docs](https://www.prisma.io/docs/reference/tools-and-interfaces/prisma-client/raw-database-access).
   */
  $queryRaw<T = unknown>(query: TemplateStringsArray | Prisma.Sql, ...values: any[]): Prisma.PrismaPromise<T>;

  /**
   * Performs a raw query and returns the `SELECT` data.
   * Susceptible to SQL injections, see documentation.
   * @example
   * ```
   * const result = await prisma.$queryRawUnsafe('SELECT * FROM User WHERE id = $1 OR email = $2;', 1, 'user@email.com')
   * ```
   *
   * Read more in our [docs](https://www.prisma.io/docs/reference/tools-and-interfaces/prisma-client/raw-database-access).
   */
  $queryRawUnsafe<T = unknown>(query: string, ...values: any[]): Prisma.PrismaPromise<T>;


  /**
   * Allows the running of a sequence of read/write operations that are guaranteed to either succeed or fail as a whole.
   * @example
   * ```
   * const [george, bob, alice] = await prisma.$transaction([
   *   prisma.user.create({ data: { name: 'George' } }),
   *   prisma.user.create({ data: { name: 'Bob' } }),
   *   prisma.user.create({ data: { name: 'Alice' } }),
   * ])
   * ```
   * 
   * Read more in our [docs](https://www.prisma.io/docs/concepts/components/prisma-client/transactions).
   */
  $transaction<P extends Prisma.PrismaPromise<any>[]>(arg: [...P], options?: { isolationLevel?: Prisma.TransactionIsolationLevel }): $Utils.JsPromise<runtime.Types.Utils.UnwrapTuple<P>>

  $transaction<R>(fn: (prisma: Omit<PrismaClient, runtime.ITXClientDenyList>) => $Utils.JsPromise<R>, options?: { maxWait?: number, timeout?: number, isolationLevel?: Prisma.TransactionIsolationLevel }): $Utils.JsPromise<R>


  $extends: $Extensions.ExtendsHook<"extends", Prisma.TypeMapCb<ClientOptions>, ExtArgs, $Utils.Call<Prisma.TypeMapCb<ClientOptions>, {
    extArgs: ExtArgs
  }>>

      /**
   * `prisma.user`: Exposes CRUD operations for the **User** model.
    * Example usage:
    * ```ts
    * // Fetch zero or more Users
    * const users = await prisma.user.findMany()
    * ```
    */
  get user(): Prisma.UserDelegate<ExtArgs, ClientOptions>;

  /**
   * `prisma.model`: Exposes CRUD operations for the **Model** model.
    * Example usage:
    * ```ts
    * // Fetch zero or more Models
    * const models = await prisma.model.findMany()
    * ```
    */
  get model(): Prisma.ModelDelegate<ExtArgs, ClientOptions>;

  /**
   * `prisma.license`: Exposes CRUD operations for the **License** model.
    * Example usage:
    * ```ts
    * // Fetch zero or more Licenses
    * const licenses = await prisma.license.findMany()
    * ```
    */
  get license(): Prisma.LicenseDelegate<ExtArgs, ClientOptions>;
}

export namespace Prisma {
  export import DMMF = runtime.DMMF

  export type PrismaPromise<T> = $Public.PrismaPromise<T>

  /**
   * Validator
   */
  export import validator = runtime.Public.validator

  /**
   * Prisma Errors
   */
  export import PrismaClientKnownRequestError = runtime.PrismaClientKnownRequestError
  export import PrismaClientUnknownRequestError = runtime.PrismaClientUnknownRequestError
  export import PrismaClientRustPanicError = runtime.PrismaClientRustPanicError
  export import PrismaClientInitializationError = runtime.PrismaClientInitializationError
  export import PrismaClientValidationError = runtime.PrismaClientValidationError

  /**
   * Re-export of sql-template-tag
   */
  export import sql = runtime.sqltag
  export import empty = runtime.empty
  export import join = runtime.join
  export import raw = runtime.raw
  export import Sql = runtime.Sql



  /**
   * Decimal.js
   */
  export import Decimal = runtime.Decimal

  export type DecimalJsLike = runtime.DecimalJsLike

  /**
   * Metrics
   */
  export type Metrics = runtime.Metrics
  export type Metric<T> = runtime.Metric<T>
  export type MetricHistogram = runtime.MetricHistogram
  export type MetricHistogramBucket = runtime.MetricHistogramBucket

  /**
  * Extensions
  */
  export import Extension = $Extensions.UserArgs
  export import getExtensionContext = runtime.Extensions.getExtensionContext
  export import Args = $Public.Args
  export import Payload = $Public.Payload
  export import Result = $Public.Result
  export import Exact = $Public.Exact

  /**
   * Prisma Client JS version: 6.8.1
   * Query Engine version: 2060c79ba17c6bb9f5823312b6f6b7f4a845738e
   */
  export type PrismaVersion = {
    client: string
  }

  export const prismaVersion: PrismaVersion

  /**
   * Utility Types
   */


  export import JsonObject = runtime.JsonObject
  export import JsonArray = runtime.JsonArray
  export import JsonValue = runtime.JsonValue
  export import InputJsonObject = runtime.InputJsonObject
  export import InputJsonArray = runtime.InputJsonArray
  export import InputJsonValue = runtime.InputJsonValue

  /**
   * Types of the values used to represent different kinds of `null` values when working with JSON fields.
   *
   * @see https://www.prisma.io/docs/concepts/components/prisma-client/working-with-fields/working-with-json-fields#filtering-on-a-json-field
   */
  namespace NullTypes {
    /**
    * Type of `Prisma.DbNull`.
    *
    * You cannot use other instances of this class. Please use the `Prisma.DbNull` value.
    *
    * @see https://www.prisma.io/docs/concepts/components/prisma-client/working-with-fields/working-with-json-fields#filtering-on-a-json-field
    */
    class DbNull {
      private DbNull: never
      private constructor()
    }

    /**
    * Type of `Prisma.JsonNull`.
    *
    * You cannot use other instances of this class. Please use the `Prisma.JsonNull` value.
    *
    * @see https://www.prisma.io/docs/concepts/components/prisma-client/working-with-fields/working-with-json-fields#filtering-on-a-json-field
    */
    class JsonNull {
      private JsonNull: never
      private constructor()
    }

    /**
    * Type of `Prisma.AnyNull`.
    *
    * You cannot use other instances of this class. Please use the `Prisma.AnyNull` value.
    *
    * @see https://www.prisma.io/docs/concepts/components/prisma-client/working-with-fields/working-with-json-fields#filtering-on-a-json-field
    */
    class AnyNull {
      private AnyNull: never
      private constructor()
    }
  }

  /**
   * Helper for filtering JSON entries that have `null` on the database (empty on the db)
   *
   * @see https://www.prisma.io/docs/concepts/components/prisma-client/working-with-fields/working-with-json-fields#filtering-on-a-json-field
   */
  export const DbNull: NullTypes.DbNull

  /**
   * Helper for filtering JSON entries that have JSON `null` values (not empty on the db)
   *
   * @see https://www.prisma.io/docs/concepts/components/prisma-client/working-with-fields/working-with-json-fields#filtering-on-a-json-field
   */
  export const JsonNull: NullTypes.JsonNull

  /**
   * Helper for filtering JSON entries that are `Prisma.DbNull` or `Prisma.JsonNull`
   *
   * @see https://www.prisma.io/docs/concepts/components/prisma-client/working-with-fields/working-with-json-fields#filtering-on-a-json-field
   */
  export const AnyNull: NullTypes.AnyNull

  type SelectAndInclude = {
    select: any
    include: any
  }

  type SelectAndOmit = {
    select: any
    omit: any
  }

  /**
   * Get the type of the value, that the Promise holds.
   */
  export type PromiseType<T extends PromiseLike<any>> = T extends PromiseLike<infer U> ? U : T;

  /**
   * Get the return type of a function which returns a Promise.
   */
  export type PromiseReturnType<T extends (...args: any) => $Utils.JsPromise<any>> = PromiseType<ReturnType<T>>

  /**
   * From T, pick a set of properties whose keys are in the union K
   */
  type Prisma__Pick<T, K extends keyof T> = {
      [P in K]: T[P];
  };


  export type Enumerable<T> = T | Array<T>;

  export type RequiredKeys<T> = {
    [K in keyof T]-?: {} extends Prisma__Pick<T, K> ? never : K
  }[keyof T]

  export type TruthyKeys<T> = keyof {
    [K in keyof T as T[K] extends false | undefined | null ? never : K]: K
  }

  export type TrueKeys<T> = TruthyKeys<Prisma__Pick<T, RequiredKeys<T>>>

  /**
   * Subset
   * @desc From `T` pick properties that exist in `U`. Simple version of Intersection
   */
  export type Subset<T, U> = {
    [key in keyof T]: key extends keyof U ? T[key] : never;
  };

  /**
   * SelectSubset
   * @desc From `T` pick properties that exist in `U`. Simple version of Intersection.
   * Additionally, it validates, if both select and include are present. If the case, it errors.
   */
  export type SelectSubset<T, U> = {
    [key in keyof T]: key extends keyof U ? T[key] : never
  } &
    (T extends SelectAndInclude
      ? 'Please either choose `select` or `include`.'
      : T extends SelectAndOmit
        ? 'Please either choose `select` or `omit`.'
        : {})

  /**
   * Subset + Intersection
   * @desc From `T` pick properties that exist in `U` and intersect `K`
   */
  export type SubsetIntersection<T, U, K> = {
    [key in keyof T]: key extends keyof U ? T[key] : never
  } &
    K

  type Without<T, U> = { [P in Exclude<keyof T, keyof U>]?: never };

  /**
   * XOR is needed to have a real mutually exclusive union type
   * https://stackoverflow.com/questions/42123407/does-typescript-support-mutually-exclusive-types
   */
  type XOR<T, U> =
    T extends object ?
    U extends object ?
      (Without<T, U> & U) | (Without<U, T> & T)
    : U : T


  /**
   * Is T a Record?
   */
  type IsObject<T extends any> = T extends Array<any>
  ? False
  : T extends Date
  ? False
  : T extends Uint8Array
  ? False
  : T extends BigInt
  ? False
  : T extends object
  ? True
  : False


  /**
   * If it's T[], return T
   */
  export type UnEnumerate<T extends unknown> = T extends Array<infer U> ? U : T

  /**
   * From ts-toolbelt
   */

  type __Either<O extends object, K extends Key> = Omit<O, K> &
    {
      // Merge all but K
      [P in K]: Prisma__Pick<O, P & keyof O> // With K possibilities
    }[K]

  type EitherStrict<O extends object, K extends Key> = Strict<__Either<O, K>>

  type EitherLoose<O extends object, K extends Key> = ComputeRaw<__Either<O, K>>

  type _Either<
    O extends object,
    K extends Key,
    strict extends Boolean
  > = {
    1: EitherStrict<O, K>
    0: EitherLoose<O, K>
  }[strict]

  type Either<
    O extends object,
    K extends Key,
    strict extends Boolean = 1
  > = O extends unknown ? _Either<O, K, strict> : never

  export type Union = any

  type PatchUndefined<O extends object, O1 extends object> = {
    [K in keyof O]: O[K] extends undefined ? At<O1, K> : O[K]
  } & {}

  /** Helper Types for "Merge" **/
  export type IntersectOf<U extends Union> = (
    U extends unknown ? (k: U) => void : never
  ) extends (k: infer I) => void
    ? I
    : never

  export type Overwrite<O extends object, O1 extends object> = {
      [K in keyof O]: K extends keyof O1 ? O1[K] : O[K];
  } & {};

  type _Merge<U extends object> = IntersectOf<Overwrite<U, {
      [K in keyof U]-?: At<U, K>;
  }>>;

  type Key = string | number | symbol;
  type AtBasic<O extends object, K extends Key> = K extends keyof O ? O[K] : never;
  type AtStrict<O extends object, K extends Key> = O[K & keyof O];
  type AtLoose<O extends object, K extends Key> = O extends unknown ? AtStrict<O, K> : never;
  export type At<O extends object, K extends Key, strict extends Boolean = 1> = {
      1: AtStrict<O, K>;
      0: AtLoose<O, K>;
  }[strict];

  export type ComputeRaw<A extends any> = A extends Function ? A : {
    [K in keyof A]: A[K];
  } & {};

  export type OptionalFlat<O> = {
    [K in keyof O]?: O[K];
  } & {};

  type _Record<K extends keyof any, T> = {
    [P in K]: T;
  };

  // cause typescript not to expand types and preserve names
  type NoExpand<T> = T extends unknown ? T : never;

  // this type assumes the passed object is entirely optional
  type AtLeast<O extends object, K extends string> = NoExpand<
    O extends unknown
    ? | (K extends keyof O ? { [P in K]: O[P] } & O : O)
      | {[P in keyof O as P extends K ? P : never]-?: O[P]} & O
    : never>;

  type _Strict<U, _U = U> = U extends unknown ? U & OptionalFlat<_Record<Exclude<Keys<_U>, keyof U>, never>> : never;

  export type Strict<U extends object> = ComputeRaw<_Strict<U>>;
  /** End Helper Types for "Merge" **/

  export type Merge<U extends object> = ComputeRaw<_Merge<Strict<U>>>;

  /**
  A [[Boolean]]
  */
  export type Boolean = True | False

  // /**
  // 1
  // */
  export type True = 1

  /**
  0
  */
  export type False = 0

  export type Not<B extends Boolean> = {
    0: 1
    1: 0
  }[B]

  export type Extends<A1 extends any, A2 extends any> = [A1] extends [never]
    ? 0 // anything `never` is false
    : A1 extends A2
    ? 1
    : 0

  export type Has<U extends Union, U1 extends Union> = Not<
    Extends<Exclude<U1, U>, U1>
  >

  export type Or<B1 extends Boolean, B2 extends Boolean> = {
    0: {
      0: 0
      1: 1
    }
    1: {
      0: 1
      1: 1
    }
  }[B1][B2]

  export type Keys<U extends Union> = U extends unknown ? keyof U : never

  type Cast<A, B> = A extends B ? A : B;

  export const type: unique symbol;



  /**
   * Used by group by
   */

  export type GetScalarType<T, O> = O extends object ? {
    [P in keyof T]: P extends keyof O
      ? O[P]
      : never
  } : never

  type FieldPaths<
    T,
    U = Omit<T, '_avg' | '_sum' | '_count' | '_min' | '_max'>
  > = IsObject<T> extends True ? U : T

  type GetHavingFields<T> = {
    [K in keyof T]: Or<
      Or<Extends<'OR', K>, Extends<'AND', K>>,
      Extends<'NOT', K>
    > extends True
      ? // infer is only needed to not hit TS limit
        // based on the brilliant idea of Pierre-Antoine Mills
        // https://github.com/microsoft/TypeScript/issues/30188#issuecomment-478938437
        T[K] extends infer TK
        ? GetHavingFields<UnEnumerate<TK> extends object ? Merge<UnEnumerate<TK>> : never>
        : never
      : {} extends FieldPaths<T[K]>
      ? never
      : K
  }[keyof T]

  /**
   * Convert tuple to union
   */
  type _TupleToUnion<T> = T extends (infer E)[] ? E : never
  type TupleToUnion<K extends readonly any[]> = _TupleToUnion<K>
  type MaybeTupleToUnion<T> = T extends any[] ? TupleToUnion<T> : T

  /**
   * Like `Pick`, but additionally can also accept an array of keys
   */
  type PickEnumerable<T, K extends Enumerable<keyof T> | keyof T> = Prisma__Pick<T, MaybeTupleToUnion<K>>

  /**
   * Exclude all keys with underscores
   */
  type ExcludeUnderscoreKeys<T extends string> = T extends `_${string}` ? never : T


  export type FieldRef<Model, FieldType> = runtime.FieldRef<Model, FieldType>

  type FieldRefInputType<Model, FieldType> = Model extends never ? never : FieldRef<Model, FieldType>


  export const ModelName: {
    User: 'User',
    Model: 'Model',
    License: 'License'
  };

  export type ModelName = (typeof ModelName)[keyof typeof ModelName]


  export type Datasources = {
    db?: Datasource
  }

  interface TypeMapCb<ClientOptions = {}> extends $Utils.Fn<{extArgs: $Extensions.InternalArgs }, $Utils.Record<string, any>> {
    returns: Prisma.TypeMap<this['params']['extArgs'], ClientOptions extends { omit: infer OmitOptions } ? OmitOptions : {}>
  }

  export type TypeMap<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs, GlobalOmitOptions = {}> = {
    globalOmitOptions: {
      omit: GlobalOmitOptions
    }
    meta: {
      modelProps: "user" | "model" | "license"
      txIsolationLevel: Prisma.TransactionIsolationLevel
    }
    model: {
      User: {
        payload: Prisma.$UserPayload<ExtArgs>
        fields: Prisma.UserFieldRefs
        operations: {
          findUnique: {
            args: Prisma.UserFindUniqueArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload> | null
          }
          findUniqueOrThrow: {
            args: Prisma.UserFindUniqueOrThrowArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload>
          }
          findFirst: {
            args: Prisma.UserFindFirstArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload> | null
          }
          findFirstOrThrow: {
            args: Prisma.UserFindFirstOrThrowArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload>
          }
          findMany: {
            args: Prisma.UserFindManyArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload>[]
          }
          create: {
            args: Prisma.UserCreateArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload>
          }
          createMany: {
            args: Prisma.UserCreateManyArgs<ExtArgs>
            result: BatchPayload
          }
          delete: {
            args: Prisma.UserDeleteArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload>
          }
          update: {
            args: Prisma.UserUpdateArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload>
          }
          deleteMany: {
            args: Prisma.UserDeleteManyArgs<ExtArgs>
            result: BatchPayload
          }
          updateMany: {
            args: Prisma.UserUpdateManyArgs<ExtArgs>
            result: BatchPayload
          }
          upsert: {
            args: Prisma.UserUpsertArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$UserPayload>
          }
          aggregate: {
            args: Prisma.UserAggregateArgs<ExtArgs>
            result: $Utils.Optional<AggregateUser>
          }
          groupBy: {
            args: Prisma.UserGroupByArgs<ExtArgs>
            result: $Utils.Optional<UserGroupByOutputType>[]
          }
          count: {
            args: Prisma.UserCountArgs<ExtArgs>
            result: $Utils.Optional<UserCountAggregateOutputType> | number
          }
        }
      }
      Model: {
        payload: Prisma.$ModelPayload<ExtArgs>
        fields: Prisma.ModelFieldRefs
        operations: {
          findUnique: {
            args: Prisma.ModelFindUniqueArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload> | null
          }
          findUniqueOrThrow: {
            args: Prisma.ModelFindUniqueOrThrowArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload>
          }
          findFirst: {
            args: Prisma.ModelFindFirstArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload> | null
          }
          findFirstOrThrow: {
            args: Prisma.ModelFindFirstOrThrowArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload>
          }
          findMany: {
            args: Prisma.ModelFindManyArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload>[]
          }
          create: {
            args: Prisma.ModelCreateArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload>
          }
          createMany: {
            args: Prisma.ModelCreateManyArgs<ExtArgs>
            result: BatchPayload
          }
          delete: {
            args: Prisma.ModelDeleteArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload>
          }
          update: {
            args: Prisma.ModelUpdateArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload>
          }
          deleteMany: {
            args: Prisma.ModelDeleteManyArgs<ExtArgs>
            result: BatchPayload
          }
          updateMany: {
            args: Prisma.ModelUpdateManyArgs<ExtArgs>
            result: BatchPayload
          }
          upsert: {
            args: Prisma.ModelUpsertArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$ModelPayload>
          }
          aggregate: {
            args: Prisma.ModelAggregateArgs<ExtArgs>
            result: $Utils.Optional<AggregateModel>
          }
          groupBy: {
            args: Prisma.ModelGroupByArgs<ExtArgs>
            result: $Utils.Optional<ModelGroupByOutputType>[]
          }
          count: {
            args: Prisma.ModelCountArgs<ExtArgs>
            result: $Utils.Optional<ModelCountAggregateOutputType> | number
          }
        }
      }
      License: {
        payload: Prisma.$LicensePayload<ExtArgs>
        fields: Prisma.LicenseFieldRefs
        operations: {
          findUnique: {
            args: Prisma.LicenseFindUniqueArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload> | null
          }
          findUniqueOrThrow: {
            args: Prisma.LicenseFindUniqueOrThrowArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload>
          }
          findFirst: {
            args: Prisma.LicenseFindFirstArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload> | null
          }
          findFirstOrThrow: {
            args: Prisma.LicenseFindFirstOrThrowArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload>
          }
          findMany: {
            args: Prisma.LicenseFindManyArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload>[]
          }
          create: {
            args: Prisma.LicenseCreateArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload>
          }
          createMany: {
            args: Prisma.LicenseCreateManyArgs<ExtArgs>
            result: BatchPayload
          }
          delete: {
            args: Prisma.LicenseDeleteArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload>
          }
          update: {
            args: Prisma.LicenseUpdateArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload>
          }
          deleteMany: {
            args: Prisma.LicenseDeleteManyArgs<ExtArgs>
            result: BatchPayload
          }
          updateMany: {
            args: Prisma.LicenseUpdateManyArgs<ExtArgs>
            result: BatchPayload
          }
          upsert: {
            args: Prisma.LicenseUpsertArgs<ExtArgs>
            result: $Utils.PayloadToResult<Prisma.$LicensePayload>
          }
          aggregate: {
            args: Prisma.LicenseAggregateArgs<ExtArgs>
            result: $Utils.Optional<AggregateLicense>
          }
          groupBy: {
            args: Prisma.LicenseGroupByArgs<ExtArgs>
            result: $Utils.Optional<LicenseGroupByOutputType>[]
          }
          count: {
            args: Prisma.LicenseCountArgs<ExtArgs>
            result: $Utils.Optional<LicenseCountAggregateOutputType> | number
          }
        }
      }
    }
  } & {
    other: {
      payload: any
      operations: {
        $executeRaw: {
          args: [query: TemplateStringsArray | Prisma.Sql, ...values: any[]],
          result: any
        }
        $executeRawUnsafe: {
          args: [query: string, ...values: any[]],
          result: any
        }
        $queryRaw: {
          args: [query: TemplateStringsArray | Prisma.Sql, ...values: any[]],
          result: any
        }
        $queryRawUnsafe: {
          args: [query: string, ...values: any[]],
          result: any
        }
      }
    }
  }
  export const defineExtension: $Extensions.ExtendsHook<"define", Prisma.TypeMapCb, $Extensions.DefaultArgs>
  export type DefaultPrismaClient = PrismaClient
  export type ErrorFormat = 'pretty' | 'colorless' | 'minimal'
  export interface PrismaClientOptions {
    /**
     * Overwrites the datasource url from your schema.prisma file
     */
    datasources?: Datasources
    /**
     * Overwrites the datasource url from your schema.prisma file
     */
    datasourceUrl?: string
    /**
     * @default "colorless"
     */
    errorFormat?: ErrorFormat
    /**
     * @example
     * ```
     * // Defaults to stdout
     * log: ['query', 'info', 'warn', 'error']
     * 
     * // Emit as events
     * log: [
     *   { emit: 'stdout', level: 'query' },
     *   { emit: 'stdout', level: 'info' },
     *   { emit: 'stdout', level: 'warn' }
     *   { emit: 'stdout', level: 'error' }
     * ]
     * ```
     * Read more in our [docs](https://www.prisma.io/docs/reference/tools-and-interfaces/prisma-client/logging#the-log-option).
     */
    log?: (LogLevel | LogDefinition)[]
    /**
     * The default values for transactionOptions
     * maxWait ?= 2000
     * timeout ?= 5000
     */
    transactionOptions?: {
      maxWait?: number
      timeout?: number
      isolationLevel?: Prisma.TransactionIsolationLevel
    }
    /**
     * Global configuration for omitting model fields by default.
     * 
     * @example
     * ```
     * const prisma = new PrismaClient({
     *   omit: {
     *     user: {
     *       password: true
     *     }
     *   }
     * })
     * ```
     */
    omit?: Prisma.GlobalOmitConfig
  }
  export type GlobalOmitConfig = {
    user?: UserOmit
    model?: ModelOmit
    license?: LicenseOmit
  }

  /* Types for Logging */
  export type LogLevel = 'info' | 'query' | 'warn' | 'error'
  export type LogDefinition = {
    level: LogLevel
    emit: 'stdout' | 'event'
  }

  export type GetLogType<T extends LogLevel | LogDefinition> = T extends LogDefinition ? T['emit'] extends 'event' ? T['level'] : never : never
  export type GetEvents<T extends any> = T extends Array<LogLevel | LogDefinition> ?
    GetLogType<T[0]> | GetLogType<T[1]> | GetLogType<T[2]> | GetLogType<T[3]>
    : never

  export type QueryEvent = {
    timestamp: Date
    query: string
    params: string
    duration: number
    target: string
  }

  export type LogEvent = {
    timestamp: Date
    message: string
    target: string
  }
  /* End Types for Logging */


  export type PrismaAction =
    | 'findUnique'
    | 'findUniqueOrThrow'
    | 'findMany'
    | 'findFirst'
    | 'findFirstOrThrow'
    | 'create'
    | 'createMany'
    | 'createManyAndReturn'
    | 'update'
    | 'updateMany'
    | 'updateManyAndReturn'
    | 'upsert'
    | 'delete'
    | 'deleteMany'
    | 'executeRaw'
    | 'queryRaw'
    | 'aggregate'
    | 'count'
    | 'runCommandRaw'
    | 'findRaw'
    | 'groupBy'

  /**
   * These options are being passed into the middleware as "params"
   */
  export type MiddlewareParams = {
    model?: ModelName
    action: PrismaAction
    args: any
    dataPath: string[]
    runInTransaction: boolean
  }

  /**
   * The `T` type makes sure, that the `return proceed` is not forgotten in the middleware implementation
   */
  export type Middleware<T = any> = (
    params: MiddlewareParams,
    next: (params: MiddlewareParams) => $Utils.JsPromise<T>,
  ) => $Utils.JsPromise<T>

  // tested in getLogLevel.test.ts
  export function getLogLevel(log: Array<LogLevel | LogDefinition>): LogLevel | undefined;

  /**
   * `PrismaClient` proxy available in interactive transactions.
   */
  export type TransactionClient = Omit<Prisma.DefaultPrismaClient, runtime.ITXClientDenyList>

  export type Datasource = {
    url?: string
  }

  /**
   * Count Types
   */


  /**
   * Count Type UserCountOutputType
   */

  export type UserCountOutputType = {
    licenses: number
  }

  export type UserCountOutputTypeSelect<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    licenses?: boolean | UserCountOutputTypeCountLicensesArgs
  }

  // Custom InputTypes
  /**
   * UserCountOutputType without action
   */
  export type UserCountOutputTypeDefaultArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the UserCountOutputType
     */
    select?: UserCountOutputTypeSelect<ExtArgs> | null
  }

  /**
   * UserCountOutputType without action
   */
  export type UserCountOutputTypeCountLicensesArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    where?: LicenseWhereInput
  }


  /**
   * Count Type ModelCountOutputType
   */

  export type ModelCountOutputType = {
    licenses: number
  }

  export type ModelCountOutputTypeSelect<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    licenses?: boolean | ModelCountOutputTypeCountLicensesArgs
  }

  // Custom InputTypes
  /**
   * ModelCountOutputType without action
   */
  export type ModelCountOutputTypeDefaultArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the ModelCountOutputType
     */
    select?: ModelCountOutputTypeSelect<ExtArgs> | null
  }

  /**
   * ModelCountOutputType without action
   */
  export type ModelCountOutputTypeCountLicensesArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    where?: LicenseWhereInput
  }


  /**
   * Models
   */

  /**
   * Model User
   */

  export type AggregateUser = {
    _count: UserCountAggregateOutputType | null
    _avg: UserAvgAggregateOutputType | null
    _sum: UserSumAggregateOutputType | null
    _min: UserMinAggregateOutputType | null
    _max: UserMaxAggregateOutputType | null
  }

  export type UserAvgAggregateOutputType = {
    id: number | null
  }

  export type UserSumAggregateOutputType = {
    id: number | null
  }

  export type UserMinAggregateOutputType = {
    id: number | null
    wallet_address: string | null
    created_at: Date | null
  }

  export type UserMaxAggregateOutputType = {
    id: number | null
    wallet_address: string | null
    created_at: Date | null
  }

  export type UserCountAggregateOutputType = {
    id: number
    wallet_address: number
    created_at: number
    _all: number
  }


  export type UserAvgAggregateInputType = {
    id?: true
  }

  export type UserSumAggregateInputType = {
    id?: true
  }

  export type UserMinAggregateInputType = {
    id?: true
    wallet_address?: true
    created_at?: true
  }

  export type UserMaxAggregateInputType = {
    id?: true
    wallet_address?: true
    created_at?: true
  }

  export type UserCountAggregateInputType = {
    id?: true
    wallet_address?: true
    created_at?: true
    _all?: true
  }

  export type UserAggregateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Filter which User to aggregate.
     */
    where?: UserWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Users to fetch.
     */
    orderBy?: UserOrderByWithRelationInput | UserOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the start position
     */
    cursor?: UserWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Users from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Users.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Count returned Users
    **/
    _count?: true | UserCountAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to average
    **/
    _avg?: UserAvgAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to sum
    **/
    _sum?: UserSumAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to find the minimum value
    **/
    _min?: UserMinAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to find the maximum value
    **/
    _max?: UserMaxAggregateInputType
  }

  export type GetUserAggregateType<T extends UserAggregateArgs> = {
        [P in keyof T & keyof AggregateUser]: P extends '_count' | 'count'
      ? T[P] extends true
        ? number
        : GetScalarType<T[P], AggregateUser[P]>
      : GetScalarType<T[P], AggregateUser[P]>
  }




  export type UserGroupByArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    where?: UserWhereInput
    orderBy?: UserOrderByWithAggregationInput | UserOrderByWithAggregationInput[]
    by: UserScalarFieldEnum[] | UserScalarFieldEnum
    having?: UserScalarWhereWithAggregatesInput
    take?: number
    skip?: number
    _count?: UserCountAggregateInputType | true
    _avg?: UserAvgAggregateInputType
    _sum?: UserSumAggregateInputType
    _min?: UserMinAggregateInputType
    _max?: UserMaxAggregateInputType
  }

  export type UserGroupByOutputType = {
    id: number
    wallet_address: string
    created_at: Date | null
    _count: UserCountAggregateOutputType | null
    _avg: UserAvgAggregateOutputType | null
    _sum: UserSumAggregateOutputType | null
    _min: UserMinAggregateOutputType | null
    _max: UserMaxAggregateOutputType | null
  }

  type GetUserGroupByPayload<T extends UserGroupByArgs> = Prisma.PrismaPromise<
    Array<
      PickEnumerable<UserGroupByOutputType, T['by']> &
        {
          [P in ((keyof T) & (keyof UserGroupByOutputType))]: P extends '_count'
            ? T[P] extends boolean
              ? number
              : GetScalarType<T[P], UserGroupByOutputType[P]>
            : GetScalarType<T[P], UserGroupByOutputType[P]>
        }
      >
    >


  export type UserSelect<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = $Extensions.GetSelect<{
    id?: boolean
    wallet_address?: boolean
    created_at?: boolean
    licenses?: boolean | User$licensesArgs<ExtArgs>
    _count?: boolean | UserCountOutputTypeDefaultArgs<ExtArgs>
  }, ExtArgs["result"]["user"]>



  export type UserSelectScalar = {
    id?: boolean
    wallet_address?: boolean
    created_at?: boolean
  }

  export type UserOmit<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = $Extensions.GetOmit<"id" | "wallet_address" | "created_at", ExtArgs["result"]["user"]>
  export type UserInclude<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    licenses?: boolean | User$licensesArgs<ExtArgs>
    _count?: boolean | UserCountOutputTypeDefaultArgs<ExtArgs>
  }

  export type $UserPayload<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    name: "User"
    objects: {
      licenses: Prisma.$LicensePayload<ExtArgs>[]
    }
    scalars: $Extensions.GetPayloadResult<{
      id: number
      wallet_address: string
      created_at: Date | null
    }, ExtArgs["result"]["user"]>
    composites: {}
  }

  type UserGetPayload<S extends boolean | null | undefined | UserDefaultArgs> = $Result.GetResult<Prisma.$UserPayload, S>

  type UserCountArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> =
    Omit<UserFindManyArgs, 'select' | 'include' | 'distinct' | 'omit'> & {
      select?: UserCountAggregateInputType | true
    }

  export interface UserDelegate<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs, GlobalOmitOptions = {}> {
    [K: symbol]: { types: Prisma.TypeMap<ExtArgs>['model']['User'], meta: { name: 'User' } }
    /**
     * Find zero or one User that matches the filter.
     * @param {UserFindUniqueArgs} args - Arguments to find a User
     * @example
     * // Get one User
     * const user = await prisma.user.findUnique({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findUnique<T extends UserFindUniqueArgs>(args: SelectSubset<T, UserFindUniqueArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "findUnique", GlobalOmitOptions> | null, null, ExtArgs, GlobalOmitOptions>

    /**
     * Find one User that matches the filter or throw an error with `error.code='P2025'`
     * if no matches were found.
     * @param {UserFindUniqueOrThrowArgs} args - Arguments to find a User
     * @example
     * // Get one User
     * const user = await prisma.user.findUniqueOrThrow({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findUniqueOrThrow<T extends UserFindUniqueOrThrowArgs>(args: SelectSubset<T, UserFindUniqueOrThrowArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "findUniqueOrThrow", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Find the first User that matches the filter.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {UserFindFirstArgs} args - Arguments to find a User
     * @example
     * // Get one User
     * const user = await prisma.user.findFirst({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findFirst<T extends UserFindFirstArgs>(args?: SelectSubset<T, UserFindFirstArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "findFirst", GlobalOmitOptions> | null, null, ExtArgs, GlobalOmitOptions>

    /**
     * Find the first User that matches the filter or
     * throw `PrismaKnownClientError` with `P2025` code if no matches were found.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {UserFindFirstOrThrowArgs} args - Arguments to find a User
     * @example
     * // Get one User
     * const user = await prisma.user.findFirstOrThrow({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findFirstOrThrow<T extends UserFindFirstOrThrowArgs>(args?: SelectSubset<T, UserFindFirstOrThrowArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "findFirstOrThrow", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Find zero or more Users that matches the filter.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {UserFindManyArgs} args - Arguments to filter and select certain fields only.
     * @example
     * // Get all Users
     * const users = await prisma.user.findMany()
     * 
     * // Get first 10 Users
     * const users = await prisma.user.findMany({ take: 10 })
     * 
     * // Only select the `id`
     * const userWithIdOnly = await prisma.user.findMany({ select: { id: true } })
     * 
     */
    findMany<T extends UserFindManyArgs>(args?: SelectSubset<T, UserFindManyArgs<ExtArgs>>): Prisma.PrismaPromise<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "findMany", GlobalOmitOptions>>

    /**
     * Create a User.
     * @param {UserCreateArgs} args - Arguments to create a User.
     * @example
     * // Create one User
     * const User = await prisma.user.create({
     *   data: {
     *     // ... data to create a User
     *   }
     * })
     * 
     */
    create<T extends UserCreateArgs>(args: SelectSubset<T, UserCreateArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "create", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Create many Users.
     * @param {UserCreateManyArgs} args - Arguments to create many Users.
     * @example
     * // Create many Users
     * const user = await prisma.user.createMany({
     *   data: [
     *     // ... provide data here
     *   ]
     * })
     *     
     */
    createMany<T extends UserCreateManyArgs>(args?: SelectSubset<T, UserCreateManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Delete a User.
     * @param {UserDeleteArgs} args - Arguments to delete one User.
     * @example
     * // Delete one User
     * const User = await prisma.user.delete({
     *   where: {
     *     // ... filter to delete one User
     *   }
     * })
     * 
     */
    delete<T extends UserDeleteArgs>(args: SelectSubset<T, UserDeleteArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "delete", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Update one User.
     * @param {UserUpdateArgs} args - Arguments to update one User.
     * @example
     * // Update one User
     * const user = await prisma.user.update({
     *   where: {
     *     // ... provide filter here
     *   },
     *   data: {
     *     // ... provide data here
     *   }
     * })
     * 
     */
    update<T extends UserUpdateArgs>(args: SelectSubset<T, UserUpdateArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "update", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Delete zero or more Users.
     * @param {UserDeleteManyArgs} args - Arguments to filter Users to delete.
     * @example
     * // Delete a few Users
     * const { count } = await prisma.user.deleteMany({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     * 
     */
    deleteMany<T extends UserDeleteManyArgs>(args?: SelectSubset<T, UserDeleteManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Update zero or more Users.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {UserUpdateManyArgs} args - Arguments to update one or more rows.
     * @example
     * // Update many Users
     * const user = await prisma.user.updateMany({
     *   where: {
     *     // ... provide filter here
     *   },
     *   data: {
     *     // ... provide data here
     *   }
     * })
     * 
     */
    updateMany<T extends UserUpdateManyArgs>(args: SelectSubset<T, UserUpdateManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Create or update one User.
     * @param {UserUpsertArgs} args - Arguments to update or create a User.
     * @example
     * // Update or create a User
     * const user = await prisma.user.upsert({
     *   create: {
     *     // ... data to create a User
     *   },
     *   update: {
     *     // ... in case it already exists, update
     *   },
     *   where: {
     *     // ... the filter for the User we want to update
     *   }
     * })
     */
    upsert<T extends UserUpsertArgs>(args: SelectSubset<T, UserUpsertArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "upsert", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>


    /**
     * Count the number of Users.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {UserCountArgs} args - Arguments to filter Users to count.
     * @example
     * // Count the number of Users
     * const count = await prisma.user.count({
     *   where: {
     *     // ... the filter for the Users we want to count
     *   }
     * })
    **/
    count<T extends UserCountArgs>(
      args?: Subset<T, UserCountArgs>,
    ): Prisma.PrismaPromise<
      T extends $Utils.Record<'select', any>
        ? T['select'] extends true
          ? number
          : GetScalarType<T['select'], UserCountAggregateOutputType>
        : number
    >

    /**
     * Allows you to perform aggregations operations on a User.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {UserAggregateArgs} args - Select which aggregations you would like to apply and on what fields.
     * @example
     * // Ordered by age ascending
     * // Where email contains prisma.io
     * // Limited to the 10 users
     * const aggregations = await prisma.user.aggregate({
     *   _avg: {
     *     age: true,
     *   },
     *   where: {
     *     email: {
     *       contains: "prisma.io",
     *     },
     *   },
     *   orderBy: {
     *     age: "asc",
     *   },
     *   take: 10,
     * })
    **/
    aggregate<T extends UserAggregateArgs>(args: Subset<T, UserAggregateArgs>): Prisma.PrismaPromise<GetUserAggregateType<T>>

    /**
     * Group by User.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {UserGroupByArgs} args - Group by arguments.
     * @example
     * // Group by city, order by createdAt, get count
     * const result = await prisma.user.groupBy({
     *   by: ['city', 'createdAt'],
     *   orderBy: {
     *     createdAt: true
     *   },
     *   _count: {
     *     _all: true
     *   },
     * })
     * 
    **/
    groupBy<
      T extends UserGroupByArgs,
      HasSelectOrTake extends Or<
        Extends<'skip', Keys<T>>,
        Extends<'take', Keys<T>>
      >,
      OrderByArg extends True extends HasSelectOrTake
        ? { orderBy: UserGroupByArgs['orderBy'] }
        : { orderBy?: UserGroupByArgs['orderBy'] },
      OrderFields extends ExcludeUnderscoreKeys<Keys<MaybeTupleToUnion<T['orderBy']>>>,
      ByFields extends MaybeTupleToUnion<T['by']>,
      ByValid extends Has<ByFields, OrderFields>,
      HavingFields extends GetHavingFields<T['having']>,
      HavingValid extends Has<ByFields, HavingFields>,
      ByEmpty extends T['by'] extends never[] ? True : False,
      InputErrors extends ByEmpty extends True
      ? `Error: "by" must not be empty.`
      : HavingValid extends False
      ? {
          [P in HavingFields]: P extends ByFields
            ? never
            : P extends string
            ? `Error: Field "${P}" used in "having" needs to be provided in "by".`
            : [
                Error,
                'Field ',
                P,
                ` in "having" needs to be provided in "by"`,
              ]
        }[HavingFields]
      : 'take' extends Keys<T>
      ? 'orderBy' extends Keys<T>
        ? ByValid extends True
          ? {}
          : {
              [P in OrderFields]: P extends ByFields
                ? never
                : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
            }[OrderFields]
        : 'Error: If you provide "take", you also need to provide "orderBy"'
      : 'skip' extends Keys<T>
      ? 'orderBy' extends Keys<T>
        ? ByValid extends True
          ? {}
          : {
              [P in OrderFields]: P extends ByFields
                ? never
                : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
            }[OrderFields]
        : 'Error: If you provide "skip", you also need to provide "orderBy"'
      : ByValid extends True
      ? {}
      : {
          [P in OrderFields]: P extends ByFields
            ? never
            : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
        }[OrderFields]
    >(args: SubsetIntersection<T, UserGroupByArgs, OrderByArg> & InputErrors): {} extends InputErrors ? GetUserGroupByPayload<T> : Prisma.PrismaPromise<InputErrors>
  /**
   * Fields of the User model
   */
  readonly fields: UserFieldRefs;
  }

  /**
   * The delegate class that acts as a "Promise-like" for User.
   * Why is this prefixed with `Prisma__`?
   * Because we want to prevent naming conflicts as mentioned in
   * https://github.com/prisma/prisma-client-js/issues/707
   */
  export interface Prisma__UserClient<T, Null = never, ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs, GlobalOmitOptions = {}> extends Prisma.PrismaPromise<T> {
    readonly [Symbol.toStringTag]: "PrismaPromise"
    licenses<T extends User$licensesArgs<ExtArgs> = {}>(args?: Subset<T, User$licensesArgs<ExtArgs>>): Prisma.PrismaPromise<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "findMany", GlobalOmitOptions> | Null>
    /**
     * Attaches callbacks for the resolution and/or rejection of the Promise.
     * @param onfulfilled The callback to execute when the Promise is resolved.
     * @param onrejected The callback to execute when the Promise is rejected.
     * @returns A Promise for the completion of which ever callback is executed.
     */
    then<TResult1 = T, TResult2 = never>(onfulfilled?: ((value: T) => TResult1 | PromiseLike<TResult1>) | undefined | null, onrejected?: ((reason: any) => TResult2 | PromiseLike<TResult2>) | undefined | null): $Utils.JsPromise<TResult1 | TResult2>
    /**
     * Attaches a callback for only the rejection of the Promise.
     * @param onrejected The callback to execute when the Promise is rejected.
     * @returns A Promise for the completion of the callback.
     */
    catch<TResult = never>(onrejected?: ((reason: any) => TResult | PromiseLike<TResult>) | undefined | null): $Utils.JsPromise<T | TResult>
    /**
     * Attaches a callback that is invoked when the Promise is settled (fulfilled or rejected). The
     * resolved value cannot be modified from the callback.
     * @param onfinally The callback to execute when the Promise is settled (fulfilled or rejected).
     * @returns A Promise for the completion of the callback.
     */
    finally(onfinally?: (() => void) | undefined | null): $Utils.JsPromise<T>
  }




  /**
   * Fields of the User model
   */
  interface UserFieldRefs {
    readonly id: FieldRef<"User", 'Int'>
    readonly wallet_address: FieldRef<"User", 'String'>
    readonly created_at: FieldRef<"User", 'DateTime'>
  }
    

  // Custom InputTypes
  /**
   * User findUnique
   */
  export type UserFindUniqueArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * Filter, which User to fetch.
     */
    where: UserWhereUniqueInput
  }

  /**
   * User findUniqueOrThrow
   */
  export type UserFindUniqueOrThrowArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * Filter, which User to fetch.
     */
    where: UserWhereUniqueInput
  }

  /**
   * User findFirst
   */
  export type UserFindFirstArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * Filter, which User to fetch.
     */
    where?: UserWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Users to fetch.
     */
    orderBy?: UserOrderByWithRelationInput | UserOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for searching for Users.
     */
    cursor?: UserWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Users from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Users.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/distinct Distinct Docs}
     * 
     * Filter by unique combinations of Users.
     */
    distinct?: UserScalarFieldEnum | UserScalarFieldEnum[]
  }

  /**
   * User findFirstOrThrow
   */
  export type UserFindFirstOrThrowArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * Filter, which User to fetch.
     */
    where?: UserWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Users to fetch.
     */
    orderBy?: UserOrderByWithRelationInput | UserOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for searching for Users.
     */
    cursor?: UserWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Users from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Users.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/distinct Distinct Docs}
     * 
     * Filter by unique combinations of Users.
     */
    distinct?: UserScalarFieldEnum | UserScalarFieldEnum[]
  }

  /**
   * User findMany
   */
  export type UserFindManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * Filter, which Users to fetch.
     */
    where?: UserWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Users to fetch.
     */
    orderBy?: UserOrderByWithRelationInput | UserOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for listing Users.
     */
    cursor?: UserWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Users from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Users.
     */
    skip?: number
    distinct?: UserScalarFieldEnum | UserScalarFieldEnum[]
  }

  /**
   * User create
   */
  export type UserCreateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * The data needed to create a User.
     */
    data: XOR<UserCreateInput, UserUncheckedCreateInput>
  }

  /**
   * User createMany
   */
  export type UserCreateManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * The data used to create many Users.
     */
    data: UserCreateManyInput | UserCreateManyInput[]
    skipDuplicates?: boolean
  }

  /**
   * User update
   */
  export type UserUpdateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * The data needed to update a User.
     */
    data: XOR<UserUpdateInput, UserUncheckedUpdateInput>
    /**
     * Choose, which User to update.
     */
    where: UserWhereUniqueInput
  }

  /**
   * User updateMany
   */
  export type UserUpdateManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * The data used to update Users.
     */
    data: XOR<UserUpdateManyMutationInput, UserUncheckedUpdateManyInput>
    /**
     * Filter which Users to update
     */
    where?: UserWhereInput
    /**
     * Limit how many Users to update.
     */
    limit?: number
  }

  /**
   * User upsert
   */
  export type UserUpsertArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * The filter to search for the User to update in case it exists.
     */
    where: UserWhereUniqueInput
    /**
     * In case the User found by the `where` argument doesn't exist, create a new User with this data.
     */
    create: XOR<UserCreateInput, UserUncheckedCreateInput>
    /**
     * In case the User was found with the provided `where` argument, update it with this data.
     */
    update: XOR<UserUpdateInput, UserUncheckedUpdateInput>
  }

  /**
   * User delete
   */
  export type UserDeleteArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
    /**
     * Filter which User to delete.
     */
    where: UserWhereUniqueInput
  }

  /**
   * User deleteMany
   */
  export type UserDeleteManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Filter which Users to delete
     */
    where?: UserWhereInput
    /**
     * Limit how many Users to delete.
     */
    limit?: number
  }

  /**
   * User.licenses
   */
  export type User$licensesArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    where?: LicenseWhereInput
    orderBy?: LicenseOrderByWithRelationInput | LicenseOrderByWithRelationInput[]
    cursor?: LicenseWhereUniqueInput
    take?: number
    skip?: number
    distinct?: LicenseScalarFieldEnum | LicenseScalarFieldEnum[]
  }

  /**
   * User without action
   */
  export type UserDefaultArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the User
     */
    select?: UserSelect<ExtArgs> | null
    /**
     * Omit specific fields from the User
     */
    omit?: UserOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: UserInclude<ExtArgs> | null
  }


  /**
   * Model Model
   */

  export type AggregateModel = {
    _count: ModelCountAggregateOutputType | null
    _avg: ModelAvgAggregateOutputType | null
    _sum: ModelSumAggregateOutputType | null
    _min: ModelMinAggregateOutputType | null
    _max: ModelMaxAggregateOutputType | null
  }

  export type ModelAvgAggregateOutputType = {
    id: number | null
    royalty: number | null
  }

  export type ModelSumAggregateOutputType = {
    id: number | null
    royalty: number | null
  }

  export type ModelMinAggregateOutputType = {
    id: number | null
    model_name: string | null
    description: string | null
    is_derivative: boolean | null
    royalty: number | null
    wallet_address: string | null
    created_at: Date | null
  }

  export type ModelMaxAggregateOutputType = {
    id: number | null
    model_name: string | null
    description: string | null
    is_derivative: boolean | null
    royalty: number | null
    wallet_address: string | null
    created_at: Date | null
  }

  export type ModelCountAggregateOutputType = {
    id: number
    model_name: number
    description: number
    is_derivative: number
    royalty: number
    wallet_address: number
    created_at: number
    _all: number
  }


  export type ModelAvgAggregateInputType = {
    id?: true
    royalty?: true
  }

  export type ModelSumAggregateInputType = {
    id?: true
    royalty?: true
  }

  export type ModelMinAggregateInputType = {
    id?: true
    model_name?: true
    description?: true
    is_derivative?: true
    royalty?: true
    wallet_address?: true
    created_at?: true
  }

  export type ModelMaxAggregateInputType = {
    id?: true
    model_name?: true
    description?: true
    is_derivative?: true
    royalty?: true
    wallet_address?: true
    created_at?: true
  }

  export type ModelCountAggregateInputType = {
    id?: true
    model_name?: true
    description?: true
    is_derivative?: true
    royalty?: true
    wallet_address?: true
    created_at?: true
    _all?: true
  }

  export type ModelAggregateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Filter which Model to aggregate.
     */
    where?: ModelWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Models to fetch.
     */
    orderBy?: ModelOrderByWithRelationInput | ModelOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the start position
     */
    cursor?: ModelWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Models from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Models.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Count returned Models
    **/
    _count?: true | ModelCountAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to average
    **/
    _avg?: ModelAvgAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to sum
    **/
    _sum?: ModelSumAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to find the minimum value
    **/
    _min?: ModelMinAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to find the maximum value
    **/
    _max?: ModelMaxAggregateInputType
  }

  export type GetModelAggregateType<T extends ModelAggregateArgs> = {
        [P in keyof T & keyof AggregateModel]: P extends '_count' | 'count'
      ? T[P] extends true
        ? number
        : GetScalarType<T[P], AggregateModel[P]>
      : GetScalarType<T[P], AggregateModel[P]>
  }




  export type ModelGroupByArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    where?: ModelWhereInput
    orderBy?: ModelOrderByWithAggregationInput | ModelOrderByWithAggregationInput[]
    by: ModelScalarFieldEnum[] | ModelScalarFieldEnum
    having?: ModelScalarWhereWithAggregatesInput
    take?: number
    skip?: number
    _count?: ModelCountAggregateInputType | true
    _avg?: ModelAvgAggregateInputType
    _sum?: ModelSumAggregateInputType
    _min?: ModelMinAggregateInputType
    _max?: ModelMaxAggregateInputType
  }

  export type ModelGroupByOutputType = {
    id: number
    model_name: string
    description: string
    is_derivative: boolean
    royalty: number
    wallet_address: string
    created_at: Date
    _count: ModelCountAggregateOutputType | null
    _avg: ModelAvgAggregateOutputType | null
    _sum: ModelSumAggregateOutputType | null
    _min: ModelMinAggregateOutputType | null
    _max: ModelMaxAggregateOutputType | null
  }

  type GetModelGroupByPayload<T extends ModelGroupByArgs> = Prisma.PrismaPromise<
    Array<
      PickEnumerable<ModelGroupByOutputType, T['by']> &
        {
          [P in ((keyof T) & (keyof ModelGroupByOutputType))]: P extends '_count'
            ? T[P] extends boolean
              ? number
              : GetScalarType<T[P], ModelGroupByOutputType[P]>
            : GetScalarType<T[P], ModelGroupByOutputType[P]>
        }
      >
    >


  export type ModelSelect<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = $Extensions.GetSelect<{
    id?: boolean
    model_name?: boolean
    description?: boolean
    is_derivative?: boolean
    royalty?: boolean
    wallet_address?: boolean
    created_at?: boolean
    licenses?: boolean | Model$licensesArgs<ExtArgs>
    _count?: boolean | ModelCountOutputTypeDefaultArgs<ExtArgs>
  }, ExtArgs["result"]["model"]>



  export type ModelSelectScalar = {
    id?: boolean
    model_name?: boolean
    description?: boolean
    is_derivative?: boolean
    royalty?: boolean
    wallet_address?: boolean
    created_at?: boolean
  }

  export type ModelOmit<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = $Extensions.GetOmit<"id" | "model_name" | "description" | "is_derivative" | "royalty" | "wallet_address" | "created_at", ExtArgs["result"]["model"]>
  export type ModelInclude<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    licenses?: boolean | Model$licensesArgs<ExtArgs>
    _count?: boolean | ModelCountOutputTypeDefaultArgs<ExtArgs>
  }

  export type $ModelPayload<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    name: "Model"
    objects: {
      licenses: Prisma.$LicensePayload<ExtArgs>[]
    }
    scalars: $Extensions.GetPayloadResult<{
      id: number
      model_name: string
      description: string
      is_derivative: boolean
      royalty: number
      wallet_address: string
      created_at: Date
    }, ExtArgs["result"]["model"]>
    composites: {}
  }

  type ModelGetPayload<S extends boolean | null | undefined | ModelDefaultArgs> = $Result.GetResult<Prisma.$ModelPayload, S>

  type ModelCountArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> =
    Omit<ModelFindManyArgs, 'select' | 'include' | 'distinct' | 'omit'> & {
      select?: ModelCountAggregateInputType | true
    }

  export interface ModelDelegate<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs, GlobalOmitOptions = {}> {
    [K: symbol]: { types: Prisma.TypeMap<ExtArgs>['model']['Model'], meta: { name: 'Model' } }
    /**
     * Find zero or one Model that matches the filter.
     * @param {ModelFindUniqueArgs} args - Arguments to find a Model
     * @example
     * // Get one Model
     * const model = await prisma.model.findUnique({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findUnique<T extends ModelFindUniqueArgs>(args: SelectSubset<T, ModelFindUniqueArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "findUnique", GlobalOmitOptions> | null, null, ExtArgs, GlobalOmitOptions>

    /**
     * Find one Model that matches the filter or throw an error with `error.code='P2025'`
     * if no matches were found.
     * @param {ModelFindUniqueOrThrowArgs} args - Arguments to find a Model
     * @example
     * // Get one Model
     * const model = await prisma.model.findUniqueOrThrow({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findUniqueOrThrow<T extends ModelFindUniqueOrThrowArgs>(args: SelectSubset<T, ModelFindUniqueOrThrowArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "findUniqueOrThrow", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Find the first Model that matches the filter.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {ModelFindFirstArgs} args - Arguments to find a Model
     * @example
     * // Get one Model
     * const model = await prisma.model.findFirst({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findFirst<T extends ModelFindFirstArgs>(args?: SelectSubset<T, ModelFindFirstArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "findFirst", GlobalOmitOptions> | null, null, ExtArgs, GlobalOmitOptions>

    /**
     * Find the first Model that matches the filter or
     * throw `PrismaKnownClientError` with `P2025` code if no matches were found.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {ModelFindFirstOrThrowArgs} args - Arguments to find a Model
     * @example
     * // Get one Model
     * const model = await prisma.model.findFirstOrThrow({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findFirstOrThrow<T extends ModelFindFirstOrThrowArgs>(args?: SelectSubset<T, ModelFindFirstOrThrowArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "findFirstOrThrow", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Find zero or more Models that matches the filter.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {ModelFindManyArgs} args - Arguments to filter and select certain fields only.
     * @example
     * // Get all Models
     * const models = await prisma.model.findMany()
     * 
     * // Get first 10 Models
     * const models = await prisma.model.findMany({ take: 10 })
     * 
     * // Only select the `id`
     * const modelWithIdOnly = await prisma.model.findMany({ select: { id: true } })
     * 
     */
    findMany<T extends ModelFindManyArgs>(args?: SelectSubset<T, ModelFindManyArgs<ExtArgs>>): Prisma.PrismaPromise<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "findMany", GlobalOmitOptions>>

    /**
     * Create a Model.
     * @param {ModelCreateArgs} args - Arguments to create a Model.
     * @example
     * // Create one Model
     * const Model = await prisma.model.create({
     *   data: {
     *     // ... data to create a Model
     *   }
     * })
     * 
     */
    create<T extends ModelCreateArgs>(args: SelectSubset<T, ModelCreateArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "create", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Create many Models.
     * @param {ModelCreateManyArgs} args - Arguments to create many Models.
     * @example
     * // Create many Models
     * const model = await prisma.model.createMany({
     *   data: [
     *     // ... provide data here
     *   ]
     * })
     *     
     */
    createMany<T extends ModelCreateManyArgs>(args?: SelectSubset<T, ModelCreateManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Delete a Model.
     * @param {ModelDeleteArgs} args - Arguments to delete one Model.
     * @example
     * // Delete one Model
     * const Model = await prisma.model.delete({
     *   where: {
     *     // ... filter to delete one Model
     *   }
     * })
     * 
     */
    delete<T extends ModelDeleteArgs>(args: SelectSubset<T, ModelDeleteArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "delete", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Update one Model.
     * @param {ModelUpdateArgs} args - Arguments to update one Model.
     * @example
     * // Update one Model
     * const model = await prisma.model.update({
     *   where: {
     *     // ... provide filter here
     *   },
     *   data: {
     *     // ... provide data here
     *   }
     * })
     * 
     */
    update<T extends ModelUpdateArgs>(args: SelectSubset<T, ModelUpdateArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "update", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Delete zero or more Models.
     * @param {ModelDeleteManyArgs} args - Arguments to filter Models to delete.
     * @example
     * // Delete a few Models
     * const { count } = await prisma.model.deleteMany({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     * 
     */
    deleteMany<T extends ModelDeleteManyArgs>(args?: SelectSubset<T, ModelDeleteManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Update zero or more Models.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {ModelUpdateManyArgs} args - Arguments to update one or more rows.
     * @example
     * // Update many Models
     * const model = await prisma.model.updateMany({
     *   where: {
     *     // ... provide filter here
     *   },
     *   data: {
     *     // ... provide data here
     *   }
     * })
     * 
     */
    updateMany<T extends ModelUpdateManyArgs>(args: SelectSubset<T, ModelUpdateManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Create or update one Model.
     * @param {ModelUpsertArgs} args - Arguments to update or create a Model.
     * @example
     * // Update or create a Model
     * const model = await prisma.model.upsert({
     *   create: {
     *     // ... data to create a Model
     *   },
     *   update: {
     *     // ... in case it already exists, update
     *   },
     *   where: {
     *     // ... the filter for the Model we want to update
     *   }
     * })
     */
    upsert<T extends ModelUpsertArgs>(args: SelectSubset<T, ModelUpsertArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "upsert", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>


    /**
     * Count the number of Models.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {ModelCountArgs} args - Arguments to filter Models to count.
     * @example
     * // Count the number of Models
     * const count = await prisma.model.count({
     *   where: {
     *     // ... the filter for the Models we want to count
     *   }
     * })
    **/
    count<T extends ModelCountArgs>(
      args?: Subset<T, ModelCountArgs>,
    ): Prisma.PrismaPromise<
      T extends $Utils.Record<'select', any>
        ? T['select'] extends true
          ? number
          : GetScalarType<T['select'], ModelCountAggregateOutputType>
        : number
    >

    /**
     * Allows you to perform aggregations operations on a Model.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {ModelAggregateArgs} args - Select which aggregations you would like to apply and on what fields.
     * @example
     * // Ordered by age ascending
     * // Where email contains prisma.io
     * // Limited to the 10 users
     * const aggregations = await prisma.user.aggregate({
     *   _avg: {
     *     age: true,
     *   },
     *   where: {
     *     email: {
     *       contains: "prisma.io",
     *     },
     *   },
     *   orderBy: {
     *     age: "asc",
     *   },
     *   take: 10,
     * })
    **/
    aggregate<T extends ModelAggregateArgs>(args: Subset<T, ModelAggregateArgs>): Prisma.PrismaPromise<GetModelAggregateType<T>>

    /**
     * Group by Model.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {ModelGroupByArgs} args - Group by arguments.
     * @example
     * // Group by city, order by createdAt, get count
     * const result = await prisma.user.groupBy({
     *   by: ['city', 'createdAt'],
     *   orderBy: {
     *     createdAt: true
     *   },
     *   _count: {
     *     _all: true
     *   },
     * })
     * 
    **/
    groupBy<
      T extends ModelGroupByArgs,
      HasSelectOrTake extends Or<
        Extends<'skip', Keys<T>>,
        Extends<'take', Keys<T>>
      >,
      OrderByArg extends True extends HasSelectOrTake
        ? { orderBy: ModelGroupByArgs['orderBy'] }
        : { orderBy?: ModelGroupByArgs['orderBy'] },
      OrderFields extends ExcludeUnderscoreKeys<Keys<MaybeTupleToUnion<T['orderBy']>>>,
      ByFields extends MaybeTupleToUnion<T['by']>,
      ByValid extends Has<ByFields, OrderFields>,
      HavingFields extends GetHavingFields<T['having']>,
      HavingValid extends Has<ByFields, HavingFields>,
      ByEmpty extends T['by'] extends never[] ? True : False,
      InputErrors extends ByEmpty extends True
      ? `Error: "by" must not be empty.`
      : HavingValid extends False
      ? {
          [P in HavingFields]: P extends ByFields
            ? never
            : P extends string
            ? `Error: Field "${P}" used in "having" needs to be provided in "by".`
            : [
                Error,
                'Field ',
                P,
                ` in "having" needs to be provided in "by"`,
              ]
        }[HavingFields]
      : 'take' extends Keys<T>
      ? 'orderBy' extends Keys<T>
        ? ByValid extends True
          ? {}
          : {
              [P in OrderFields]: P extends ByFields
                ? never
                : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
            }[OrderFields]
        : 'Error: If you provide "take", you also need to provide "orderBy"'
      : 'skip' extends Keys<T>
      ? 'orderBy' extends Keys<T>
        ? ByValid extends True
          ? {}
          : {
              [P in OrderFields]: P extends ByFields
                ? never
                : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
            }[OrderFields]
        : 'Error: If you provide "skip", you also need to provide "orderBy"'
      : ByValid extends True
      ? {}
      : {
          [P in OrderFields]: P extends ByFields
            ? never
            : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
        }[OrderFields]
    >(args: SubsetIntersection<T, ModelGroupByArgs, OrderByArg> & InputErrors): {} extends InputErrors ? GetModelGroupByPayload<T> : Prisma.PrismaPromise<InputErrors>
  /**
   * Fields of the Model model
   */
  readonly fields: ModelFieldRefs;
  }

  /**
   * The delegate class that acts as a "Promise-like" for Model.
   * Why is this prefixed with `Prisma__`?
   * Because we want to prevent naming conflicts as mentioned in
   * https://github.com/prisma/prisma-client-js/issues/707
   */
  export interface Prisma__ModelClient<T, Null = never, ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs, GlobalOmitOptions = {}> extends Prisma.PrismaPromise<T> {
    readonly [Symbol.toStringTag]: "PrismaPromise"
    licenses<T extends Model$licensesArgs<ExtArgs> = {}>(args?: Subset<T, Model$licensesArgs<ExtArgs>>): Prisma.PrismaPromise<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "findMany", GlobalOmitOptions> | Null>
    /**
     * Attaches callbacks for the resolution and/or rejection of the Promise.
     * @param onfulfilled The callback to execute when the Promise is resolved.
     * @param onrejected The callback to execute when the Promise is rejected.
     * @returns A Promise for the completion of which ever callback is executed.
     */
    then<TResult1 = T, TResult2 = never>(onfulfilled?: ((value: T) => TResult1 | PromiseLike<TResult1>) | undefined | null, onrejected?: ((reason: any) => TResult2 | PromiseLike<TResult2>) | undefined | null): $Utils.JsPromise<TResult1 | TResult2>
    /**
     * Attaches a callback for only the rejection of the Promise.
     * @param onrejected The callback to execute when the Promise is rejected.
     * @returns A Promise for the completion of the callback.
     */
    catch<TResult = never>(onrejected?: ((reason: any) => TResult | PromiseLike<TResult>) | undefined | null): $Utils.JsPromise<T | TResult>
    /**
     * Attaches a callback that is invoked when the Promise is settled (fulfilled or rejected). The
     * resolved value cannot be modified from the callback.
     * @param onfinally The callback to execute when the Promise is settled (fulfilled or rejected).
     * @returns A Promise for the completion of the callback.
     */
    finally(onfinally?: (() => void) | undefined | null): $Utils.JsPromise<T>
  }




  /**
   * Fields of the Model model
   */
  interface ModelFieldRefs {
    readonly id: FieldRef<"Model", 'Int'>
    readonly model_name: FieldRef<"Model", 'String'>
    readonly description: FieldRef<"Model", 'String'>
    readonly is_derivative: FieldRef<"Model", 'Boolean'>
    readonly royalty: FieldRef<"Model", 'Int'>
    readonly wallet_address: FieldRef<"Model", 'String'>
    readonly created_at: FieldRef<"Model", 'DateTime'>
  }
    

  // Custom InputTypes
  /**
   * Model findUnique
   */
  export type ModelFindUniqueArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * Filter, which Model to fetch.
     */
    where: ModelWhereUniqueInput
  }

  /**
   * Model findUniqueOrThrow
   */
  export type ModelFindUniqueOrThrowArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * Filter, which Model to fetch.
     */
    where: ModelWhereUniqueInput
  }

  /**
   * Model findFirst
   */
  export type ModelFindFirstArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * Filter, which Model to fetch.
     */
    where?: ModelWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Models to fetch.
     */
    orderBy?: ModelOrderByWithRelationInput | ModelOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for searching for Models.
     */
    cursor?: ModelWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Models from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Models.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/distinct Distinct Docs}
     * 
     * Filter by unique combinations of Models.
     */
    distinct?: ModelScalarFieldEnum | ModelScalarFieldEnum[]
  }

  /**
   * Model findFirstOrThrow
   */
  export type ModelFindFirstOrThrowArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * Filter, which Model to fetch.
     */
    where?: ModelWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Models to fetch.
     */
    orderBy?: ModelOrderByWithRelationInput | ModelOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for searching for Models.
     */
    cursor?: ModelWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Models from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Models.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/distinct Distinct Docs}
     * 
     * Filter by unique combinations of Models.
     */
    distinct?: ModelScalarFieldEnum | ModelScalarFieldEnum[]
  }

  /**
   * Model findMany
   */
  export type ModelFindManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * Filter, which Models to fetch.
     */
    where?: ModelWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Models to fetch.
     */
    orderBy?: ModelOrderByWithRelationInput | ModelOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for listing Models.
     */
    cursor?: ModelWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Models from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Models.
     */
    skip?: number
    distinct?: ModelScalarFieldEnum | ModelScalarFieldEnum[]
  }

  /**
   * Model create
   */
  export type ModelCreateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * The data needed to create a Model.
     */
    data: XOR<ModelCreateInput, ModelUncheckedCreateInput>
  }

  /**
   * Model createMany
   */
  export type ModelCreateManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * The data used to create many Models.
     */
    data: ModelCreateManyInput | ModelCreateManyInput[]
    skipDuplicates?: boolean
  }

  /**
   * Model update
   */
  export type ModelUpdateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * The data needed to update a Model.
     */
    data: XOR<ModelUpdateInput, ModelUncheckedUpdateInput>
    /**
     * Choose, which Model to update.
     */
    where: ModelWhereUniqueInput
  }

  /**
   * Model updateMany
   */
  export type ModelUpdateManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * The data used to update Models.
     */
    data: XOR<ModelUpdateManyMutationInput, ModelUncheckedUpdateManyInput>
    /**
     * Filter which Models to update
     */
    where?: ModelWhereInput
    /**
     * Limit how many Models to update.
     */
    limit?: number
  }

  /**
   * Model upsert
   */
  export type ModelUpsertArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * The filter to search for the Model to update in case it exists.
     */
    where: ModelWhereUniqueInput
    /**
     * In case the Model found by the `where` argument doesn't exist, create a new Model with this data.
     */
    create: XOR<ModelCreateInput, ModelUncheckedCreateInput>
    /**
     * In case the Model was found with the provided `where` argument, update it with this data.
     */
    update: XOR<ModelUpdateInput, ModelUncheckedUpdateInput>
  }

  /**
   * Model delete
   */
  export type ModelDeleteArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
    /**
     * Filter which Model to delete.
     */
    where: ModelWhereUniqueInput
  }

  /**
   * Model deleteMany
   */
  export type ModelDeleteManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Filter which Models to delete
     */
    where?: ModelWhereInput
    /**
     * Limit how many Models to delete.
     */
    limit?: number
  }

  /**
   * Model.licenses
   */
  export type Model$licensesArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    where?: LicenseWhereInput
    orderBy?: LicenseOrderByWithRelationInput | LicenseOrderByWithRelationInput[]
    cursor?: LicenseWhereUniqueInput
    take?: number
    skip?: number
    distinct?: LicenseScalarFieldEnum | LicenseScalarFieldEnum[]
  }

  /**
   * Model without action
   */
  export type ModelDefaultArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the Model
     */
    select?: ModelSelect<ExtArgs> | null
    /**
     * Omit specific fields from the Model
     */
    omit?: ModelOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: ModelInclude<ExtArgs> | null
  }


  /**
   * Model License
   */

  export type AggregateLicense = {
    _count: LicenseCountAggregateOutputType | null
    _avg: LicenseAvgAggregateOutputType | null
    _sum: LicenseSumAggregateOutputType | null
    _min: LicenseMinAggregateOutputType | null
    _max: LicenseMaxAggregateOutputType | null
  }

  export type LicenseAvgAggregateOutputType = {
    id: number | null
    userId: number | null
    modelId: number | null
  }

  export type LicenseSumAggregateOutputType = {
    id: number | null
    userId: number | null
    modelId: number | null
  }

  export type LicenseMinAggregateOutputType = {
    id: number | null
    userId: number | null
    modelId: number | null
    created_at: Date | null
  }

  export type LicenseMaxAggregateOutputType = {
    id: number | null
    userId: number | null
    modelId: number | null
    created_at: Date | null
  }

  export type LicenseCountAggregateOutputType = {
    id: number
    userId: number
    modelId: number
    created_at: number
    _all: number
  }


  export type LicenseAvgAggregateInputType = {
    id?: true
    userId?: true
    modelId?: true
  }

  export type LicenseSumAggregateInputType = {
    id?: true
    userId?: true
    modelId?: true
  }

  export type LicenseMinAggregateInputType = {
    id?: true
    userId?: true
    modelId?: true
    created_at?: true
  }

  export type LicenseMaxAggregateInputType = {
    id?: true
    userId?: true
    modelId?: true
    created_at?: true
  }

  export type LicenseCountAggregateInputType = {
    id?: true
    userId?: true
    modelId?: true
    created_at?: true
    _all?: true
  }

  export type LicenseAggregateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Filter which License to aggregate.
     */
    where?: LicenseWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Licenses to fetch.
     */
    orderBy?: LicenseOrderByWithRelationInput | LicenseOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the start position
     */
    cursor?: LicenseWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Licenses from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Licenses.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Count returned Licenses
    **/
    _count?: true | LicenseCountAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to average
    **/
    _avg?: LicenseAvgAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to sum
    **/
    _sum?: LicenseSumAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to find the minimum value
    **/
    _min?: LicenseMinAggregateInputType
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/aggregations Aggregation Docs}
     * 
     * Select which fields to find the maximum value
    **/
    _max?: LicenseMaxAggregateInputType
  }

  export type GetLicenseAggregateType<T extends LicenseAggregateArgs> = {
        [P in keyof T & keyof AggregateLicense]: P extends '_count' | 'count'
      ? T[P] extends true
        ? number
        : GetScalarType<T[P], AggregateLicense[P]>
      : GetScalarType<T[P], AggregateLicense[P]>
  }




  export type LicenseGroupByArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    where?: LicenseWhereInput
    orderBy?: LicenseOrderByWithAggregationInput | LicenseOrderByWithAggregationInput[]
    by: LicenseScalarFieldEnum[] | LicenseScalarFieldEnum
    having?: LicenseScalarWhereWithAggregatesInput
    take?: number
    skip?: number
    _count?: LicenseCountAggregateInputType | true
    _avg?: LicenseAvgAggregateInputType
    _sum?: LicenseSumAggregateInputType
    _min?: LicenseMinAggregateInputType
    _max?: LicenseMaxAggregateInputType
  }

  export type LicenseGroupByOutputType = {
    id: number
    userId: number
    modelId: number
    created_at: Date
    _count: LicenseCountAggregateOutputType | null
    _avg: LicenseAvgAggregateOutputType | null
    _sum: LicenseSumAggregateOutputType | null
    _min: LicenseMinAggregateOutputType | null
    _max: LicenseMaxAggregateOutputType | null
  }

  type GetLicenseGroupByPayload<T extends LicenseGroupByArgs> = Prisma.PrismaPromise<
    Array<
      PickEnumerable<LicenseGroupByOutputType, T['by']> &
        {
          [P in ((keyof T) & (keyof LicenseGroupByOutputType))]: P extends '_count'
            ? T[P] extends boolean
              ? number
              : GetScalarType<T[P], LicenseGroupByOutputType[P]>
            : GetScalarType<T[P], LicenseGroupByOutputType[P]>
        }
      >
    >


  export type LicenseSelect<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = $Extensions.GetSelect<{
    id?: boolean
    userId?: boolean
    modelId?: boolean
    created_at?: boolean
    user?: boolean | UserDefaultArgs<ExtArgs>
    model?: boolean | ModelDefaultArgs<ExtArgs>
  }, ExtArgs["result"]["license"]>



  export type LicenseSelectScalar = {
    id?: boolean
    userId?: boolean
    modelId?: boolean
    created_at?: boolean
  }

  export type LicenseOmit<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = $Extensions.GetOmit<"id" | "userId" | "modelId" | "created_at", ExtArgs["result"]["license"]>
  export type LicenseInclude<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    user?: boolean | UserDefaultArgs<ExtArgs>
    model?: boolean | ModelDefaultArgs<ExtArgs>
  }

  export type $LicensePayload<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    name: "License"
    objects: {
      user: Prisma.$UserPayload<ExtArgs>
      model: Prisma.$ModelPayload<ExtArgs>
    }
    scalars: $Extensions.GetPayloadResult<{
      id: number
      userId: number
      modelId: number
      created_at: Date
    }, ExtArgs["result"]["license"]>
    composites: {}
  }

  type LicenseGetPayload<S extends boolean | null | undefined | LicenseDefaultArgs> = $Result.GetResult<Prisma.$LicensePayload, S>

  type LicenseCountArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> =
    Omit<LicenseFindManyArgs, 'select' | 'include' | 'distinct' | 'omit'> & {
      select?: LicenseCountAggregateInputType | true
    }

  export interface LicenseDelegate<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs, GlobalOmitOptions = {}> {
    [K: symbol]: { types: Prisma.TypeMap<ExtArgs>['model']['License'], meta: { name: 'License' } }
    /**
     * Find zero or one License that matches the filter.
     * @param {LicenseFindUniqueArgs} args - Arguments to find a License
     * @example
     * // Get one License
     * const license = await prisma.license.findUnique({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findUnique<T extends LicenseFindUniqueArgs>(args: SelectSubset<T, LicenseFindUniqueArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "findUnique", GlobalOmitOptions> | null, null, ExtArgs, GlobalOmitOptions>

    /**
     * Find one License that matches the filter or throw an error with `error.code='P2025'`
     * if no matches were found.
     * @param {LicenseFindUniqueOrThrowArgs} args - Arguments to find a License
     * @example
     * // Get one License
     * const license = await prisma.license.findUniqueOrThrow({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findUniqueOrThrow<T extends LicenseFindUniqueOrThrowArgs>(args: SelectSubset<T, LicenseFindUniqueOrThrowArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "findUniqueOrThrow", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Find the first License that matches the filter.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {LicenseFindFirstArgs} args - Arguments to find a License
     * @example
     * // Get one License
     * const license = await prisma.license.findFirst({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findFirst<T extends LicenseFindFirstArgs>(args?: SelectSubset<T, LicenseFindFirstArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "findFirst", GlobalOmitOptions> | null, null, ExtArgs, GlobalOmitOptions>

    /**
     * Find the first License that matches the filter or
     * throw `PrismaKnownClientError` with `P2025` code if no matches were found.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {LicenseFindFirstOrThrowArgs} args - Arguments to find a License
     * @example
     * // Get one License
     * const license = await prisma.license.findFirstOrThrow({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     */
    findFirstOrThrow<T extends LicenseFindFirstOrThrowArgs>(args?: SelectSubset<T, LicenseFindFirstOrThrowArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "findFirstOrThrow", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Find zero or more Licenses that matches the filter.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {LicenseFindManyArgs} args - Arguments to filter and select certain fields only.
     * @example
     * // Get all Licenses
     * const licenses = await prisma.license.findMany()
     * 
     * // Get first 10 Licenses
     * const licenses = await prisma.license.findMany({ take: 10 })
     * 
     * // Only select the `id`
     * const licenseWithIdOnly = await prisma.license.findMany({ select: { id: true } })
     * 
     */
    findMany<T extends LicenseFindManyArgs>(args?: SelectSubset<T, LicenseFindManyArgs<ExtArgs>>): Prisma.PrismaPromise<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "findMany", GlobalOmitOptions>>

    /**
     * Create a License.
     * @param {LicenseCreateArgs} args - Arguments to create a License.
     * @example
     * // Create one License
     * const License = await prisma.license.create({
     *   data: {
     *     // ... data to create a License
     *   }
     * })
     * 
     */
    create<T extends LicenseCreateArgs>(args: SelectSubset<T, LicenseCreateArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "create", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Create many Licenses.
     * @param {LicenseCreateManyArgs} args - Arguments to create many Licenses.
     * @example
     * // Create many Licenses
     * const license = await prisma.license.createMany({
     *   data: [
     *     // ... provide data here
     *   ]
     * })
     *     
     */
    createMany<T extends LicenseCreateManyArgs>(args?: SelectSubset<T, LicenseCreateManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Delete a License.
     * @param {LicenseDeleteArgs} args - Arguments to delete one License.
     * @example
     * // Delete one License
     * const License = await prisma.license.delete({
     *   where: {
     *     // ... filter to delete one License
     *   }
     * })
     * 
     */
    delete<T extends LicenseDeleteArgs>(args: SelectSubset<T, LicenseDeleteArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "delete", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Update one License.
     * @param {LicenseUpdateArgs} args - Arguments to update one License.
     * @example
     * // Update one License
     * const license = await prisma.license.update({
     *   where: {
     *     // ... provide filter here
     *   },
     *   data: {
     *     // ... provide data here
     *   }
     * })
     * 
     */
    update<T extends LicenseUpdateArgs>(args: SelectSubset<T, LicenseUpdateArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "update", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>

    /**
     * Delete zero or more Licenses.
     * @param {LicenseDeleteManyArgs} args - Arguments to filter Licenses to delete.
     * @example
     * // Delete a few Licenses
     * const { count } = await prisma.license.deleteMany({
     *   where: {
     *     // ... provide filter here
     *   }
     * })
     * 
     */
    deleteMany<T extends LicenseDeleteManyArgs>(args?: SelectSubset<T, LicenseDeleteManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Update zero or more Licenses.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {LicenseUpdateManyArgs} args - Arguments to update one or more rows.
     * @example
     * // Update many Licenses
     * const license = await prisma.license.updateMany({
     *   where: {
     *     // ... provide filter here
     *   },
     *   data: {
     *     // ... provide data here
     *   }
     * })
     * 
     */
    updateMany<T extends LicenseUpdateManyArgs>(args: SelectSubset<T, LicenseUpdateManyArgs<ExtArgs>>): Prisma.PrismaPromise<BatchPayload>

    /**
     * Create or update one License.
     * @param {LicenseUpsertArgs} args - Arguments to update or create a License.
     * @example
     * // Update or create a License
     * const license = await prisma.license.upsert({
     *   create: {
     *     // ... data to create a License
     *   },
     *   update: {
     *     // ... in case it already exists, update
     *   },
     *   where: {
     *     // ... the filter for the License we want to update
     *   }
     * })
     */
    upsert<T extends LicenseUpsertArgs>(args: SelectSubset<T, LicenseUpsertArgs<ExtArgs>>): Prisma__LicenseClient<$Result.GetResult<Prisma.$LicensePayload<ExtArgs>, T, "upsert", GlobalOmitOptions>, never, ExtArgs, GlobalOmitOptions>


    /**
     * Count the number of Licenses.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {LicenseCountArgs} args - Arguments to filter Licenses to count.
     * @example
     * // Count the number of Licenses
     * const count = await prisma.license.count({
     *   where: {
     *     // ... the filter for the Licenses we want to count
     *   }
     * })
    **/
    count<T extends LicenseCountArgs>(
      args?: Subset<T, LicenseCountArgs>,
    ): Prisma.PrismaPromise<
      T extends $Utils.Record<'select', any>
        ? T['select'] extends true
          ? number
          : GetScalarType<T['select'], LicenseCountAggregateOutputType>
        : number
    >

    /**
     * Allows you to perform aggregations operations on a License.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {LicenseAggregateArgs} args - Select which aggregations you would like to apply and on what fields.
     * @example
     * // Ordered by age ascending
     * // Where email contains prisma.io
     * // Limited to the 10 users
     * const aggregations = await prisma.user.aggregate({
     *   _avg: {
     *     age: true,
     *   },
     *   where: {
     *     email: {
     *       contains: "prisma.io",
     *     },
     *   },
     *   orderBy: {
     *     age: "asc",
     *   },
     *   take: 10,
     * })
    **/
    aggregate<T extends LicenseAggregateArgs>(args: Subset<T, LicenseAggregateArgs>): Prisma.PrismaPromise<GetLicenseAggregateType<T>>

    /**
     * Group by License.
     * Note, that providing `undefined` is treated as the value not being there.
     * Read more here: https://pris.ly/d/null-undefined
     * @param {LicenseGroupByArgs} args - Group by arguments.
     * @example
     * // Group by city, order by createdAt, get count
     * const result = await prisma.user.groupBy({
     *   by: ['city', 'createdAt'],
     *   orderBy: {
     *     createdAt: true
     *   },
     *   _count: {
     *     _all: true
     *   },
     * })
     * 
    **/
    groupBy<
      T extends LicenseGroupByArgs,
      HasSelectOrTake extends Or<
        Extends<'skip', Keys<T>>,
        Extends<'take', Keys<T>>
      >,
      OrderByArg extends True extends HasSelectOrTake
        ? { orderBy: LicenseGroupByArgs['orderBy'] }
        : { orderBy?: LicenseGroupByArgs['orderBy'] },
      OrderFields extends ExcludeUnderscoreKeys<Keys<MaybeTupleToUnion<T['orderBy']>>>,
      ByFields extends MaybeTupleToUnion<T['by']>,
      ByValid extends Has<ByFields, OrderFields>,
      HavingFields extends GetHavingFields<T['having']>,
      HavingValid extends Has<ByFields, HavingFields>,
      ByEmpty extends T['by'] extends never[] ? True : False,
      InputErrors extends ByEmpty extends True
      ? `Error: "by" must not be empty.`
      : HavingValid extends False
      ? {
          [P in HavingFields]: P extends ByFields
            ? never
            : P extends string
            ? `Error: Field "${P}" used in "having" needs to be provided in "by".`
            : [
                Error,
                'Field ',
                P,
                ` in "having" needs to be provided in "by"`,
              ]
        }[HavingFields]
      : 'take' extends Keys<T>
      ? 'orderBy' extends Keys<T>
        ? ByValid extends True
          ? {}
          : {
              [P in OrderFields]: P extends ByFields
                ? never
                : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
            }[OrderFields]
        : 'Error: If you provide "take", you also need to provide "orderBy"'
      : 'skip' extends Keys<T>
      ? 'orderBy' extends Keys<T>
        ? ByValid extends True
          ? {}
          : {
              [P in OrderFields]: P extends ByFields
                ? never
                : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
            }[OrderFields]
        : 'Error: If you provide "skip", you also need to provide "orderBy"'
      : ByValid extends True
      ? {}
      : {
          [P in OrderFields]: P extends ByFields
            ? never
            : `Error: Field "${P}" in "orderBy" needs to be provided in "by"`
        }[OrderFields]
    >(args: SubsetIntersection<T, LicenseGroupByArgs, OrderByArg> & InputErrors): {} extends InputErrors ? GetLicenseGroupByPayload<T> : Prisma.PrismaPromise<InputErrors>
  /**
   * Fields of the License model
   */
  readonly fields: LicenseFieldRefs;
  }

  /**
   * The delegate class that acts as a "Promise-like" for License.
   * Why is this prefixed with `Prisma__`?
   * Because we want to prevent naming conflicts as mentioned in
   * https://github.com/prisma/prisma-client-js/issues/707
   */
  export interface Prisma__LicenseClient<T, Null = never, ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs, GlobalOmitOptions = {}> extends Prisma.PrismaPromise<T> {
    readonly [Symbol.toStringTag]: "PrismaPromise"
    user<T extends UserDefaultArgs<ExtArgs> = {}>(args?: Subset<T, UserDefaultArgs<ExtArgs>>): Prisma__UserClient<$Result.GetResult<Prisma.$UserPayload<ExtArgs>, T, "findUniqueOrThrow", GlobalOmitOptions> | Null, Null, ExtArgs, GlobalOmitOptions>
    model<T extends ModelDefaultArgs<ExtArgs> = {}>(args?: Subset<T, ModelDefaultArgs<ExtArgs>>): Prisma__ModelClient<$Result.GetResult<Prisma.$ModelPayload<ExtArgs>, T, "findUniqueOrThrow", GlobalOmitOptions> | Null, Null, ExtArgs, GlobalOmitOptions>
    /**
     * Attaches callbacks for the resolution and/or rejection of the Promise.
     * @param onfulfilled The callback to execute when the Promise is resolved.
     * @param onrejected The callback to execute when the Promise is rejected.
     * @returns A Promise for the completion of which ever callback is executed.
     */
    then<TResult1 = T, TResult2 = never>(onfulfilled?: ((value: T) => TResult1 | PromiseLike<TResult1>) | undefined | null, onrejected?: ((reason: any) => TResult2 | PromiseLike<TResult2>) | undefined | null): $Utils.JsPromise<TResult1 | TResult2>
    /**
     * Attaches a callback for only the rejection of the Promise.
     * @param onrejected The callback to execute when the Promise is rejected.
     * @returns A Promise for the completion of the callback.
     */
    catch<TResult = never>(onrejected?: ((reason: any) => TResult | PromiseLike<TResult>) | undefined | null): $Utils.JsPromise<T | TResult>
    /**
     * Attaches a callback that is invoked when the Promise is settled (fulfilled or rejected). The
     * resolved value cannot be modified from the callback.
     * @param onfinally The callback to execute when the Promise is settled (fulfilled or rejected).
     * @returns A Promise for the completion of the callback.
     */
    finally(onfinally?: (() => void) | undefined | null): $Utils.JsPromise<T>
  }




  /**
   * Fields of the License model
   */
  interface LicenseFieldRefs {
    readonly id: FieldRef<"License", 'Int'>
    readonly userId: FieldRef<"License", 'Int'>
    readonly modelId: FieldRef<"License", 'Int'>
    readonly created_at: FieldRef<"License", 'DateTime'>
  }
    

  // Custom InputTypes
  /**
   * License findUnique
   */
  export type LicenseFindUniqueArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * Filter, which License to fetch.
     */
    where: LicenseWhereUniqueInput
  }

  /**
   * License findUniqueOrThrow
   */
  export type LicenseFindUniqueOrThrowArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * Filter, which License to fetch.
     */
    where: LicenseWhereUniqueInput
  }

  /**
   * License findFirst
   */
  export type LicenseFindFirstArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * Filter, which License to fetch.
     */
    where?: LicenseWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Licenses to fetch.
     */
    orderBy?: LicenseOrderByWithRelationInput | LicenseOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for searching for Licenses.
     */
    cursor?: LicenseWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Licenses from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Licenses.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/distinct Distinct Docs}
     * 
     * Filter by unique combinations of Licenses.
     */
    distinct?: LicenseScalarFieldEnum | LicenseScalarFieldEnum[]
  }

  /**
   * License findFirstOrThrow
   */
  export type LicenseFindFirstOrThrowArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * Filter, which License to fetch.
     */
    where?: LicenseWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Licenses to fetch.
     */
    orderBy?: LicenseOrderByWithRelationInput | LicenseOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for searching for Licenses.
     */
    cursor?: LicenseWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Licenses from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Licenses.
     */
    skip?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/distinct Distinct Docs}
     * 
     * Filter by unique combinations of Licenses.
     */
    distinct?: LicenseScalarFieldEnum | LicenseScalarFieldEnum[]
  }

  /**
   * License findMany
   */
  export type LicenseFindManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * Filter, which Licenses to fetch.
     */
    where?: LicenseWhereInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/sorting Sorting Docs}
     * 
     * Determine the order of Licenses to fetch.
     */
    orderBy?: LicenseOrderByWithRelationInput | LicenseOrderByWithRelationInput[]
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination#cursor-based-pagination Cursor Docs}
     * 
     * Sets the position for listing Licenses.
     */
    cursor?: LicenseWhereUniqueInput
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Take `±n` Licenses from the position of the cursor.
     */
    take?: number
    /**
     * {@link https://www.prisma.io/docs/concepts/components/prisma-client/pagination Pagination Docs}
     * 
     * Skip the first `n` Licenses.
     */
    skip?: number
    distinct?: LicenseScalarFieldEnum | LicenseScalarFieldEnum[]
  }

  /**
   * License create
   */
  export type LicenseCreateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * The data needed to create a License.
     */
    data: XOR<LicenseCreateInput, LicenseUncheckedCreateInput>
  }

  /**
   * License createMany
   */
  export type LicenseCreateManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * The data used to create many Licenses.
     */
    data: LicenseCreateManyInput | LicenseCreateManyInput[]
    skipDuplicates?: boolean
  }

  /**
   * License update
   */
  export type LicenseUpdateArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * The data needed to update a License.
     */
    data: XOR<LicenseUpdateInput, LicenseUncheckedUpdateInput>
    /**
     * Choose, which License to update.
     */
    where: LicenseWhereUniqueInput
  }

  /**
   * License updateMany
   */
  export type LicenseUpdateManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * The data used to update Licenses.
     */
    data: XOR<LicenseUpdateManyMutationInput, LicenseUncheckedUpdateManyInput>
    /**
     * Filter which Licenses to update
     */
    where?: LicenseWhereInput
    /**
     * Limit how many Licenses to update.
     */
    limit?: number
  }

  /**
   * License upsert
   */
  export type LicenseUpsertArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * The filter to search for the License to update in case it exists.
     */
    where: LicenseWhereUniqueInput
    /**
     * In case the License found by the `where` argument doesn't exist, create a new License with this data.
     */
    create: XOR<LicenseCreateInput, LicenseUncheckedCreateInput>
    /**
     * In case the License was found with the provided `where` argument, update it with this data.
     */
    update: XOR<LicenseUpdateInput, LicenseUncheckedUpdateInput>
  }

  /**
   * License delete
   */
  export type LicenseDeleteArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
    /**
     * Filter which License to delete.
     */
    where: LicenseWhereUniqueInput
  }

  /**
   * License deleteMany
   */
  export type LicenseDeleteManyArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Filter which Licenses to delete
     */
    where?: LicenseWhereInput
    /**
     * Limit how many Licenses to delete.
     */
    limit?: number
  }

  /**
   * License without action
   */
  export type LicenseDefaultArgs<ExtArgs extends $Extensions.InternalArgs = $Extensions.DefaultArgs> = {
    /**
     * Select specific fields to fetch from the License
     */
    select?: LicenseSelect<ExtArgs> | null
    /**
     * Omit specific fields from the License
     */
    omit?: LicenseOmit<ExtArgs> | null
    /**
     * Choose, which related nodes to fetch as well
     */
    include?: LicenseInclude<ExtArgs> | null
  }


  /**
   * Enums
   */

  export const TransactionIsolationLevel: {
    ReadUncommitted: 'ReadUncommitted',
    ReadCommitted: 'ReadCommitted',
    RepeatableRead: 'RepeatableRead',
    Serializable: 'Serializable'
  };

  export type TransactionIsolationLevel = (typeof TransactionIsolationLevel)[keyof typeof TransactionIsolationLevel]


  export const UserScalarFieldEnum: {
    id: 'id',
    wallet_address: 'wallet_address',
    created_at: 'created_at'
  };

  export type UserScalarFieldEnum = (typeof UserScalarFieldEnum)[keyof typeof UserScalarFieldEnum]


  export const ModelScalarFieldEnum: {
    id: 'id',
    model_name: 'model_name',
    description: 'description',
    is_derivative: 'is_derivative',
    royalty: 'royalty',
    wallet_address: 'wallet_address',
    created_at: 'created_at'
  };

  export type ModelScalarFieldEnum = (typeof ModelScalarFieldEnum)[keyof typeof ModelScalarFieldEnum]


  export const LicenseScalarFieldEnum: {
    id: 'id',
    userId: 'userId',
    modelId: 'modelId',
    created_at: 'created_at'
  };

  export type LicenseScalarFieldEnum = (typeof LicenseScalarFieldEnum)[keyof typeof LicenseScalarFieldEnum]


  export const SortOrder: {
    asc: 'asc',
    desc: 'desc'
  };

  export type SortOrder = (typeof SortOrder)[keyof typeof SortOrder]


  export const NullsOrder: {
    first: 'first',
    last: 'last'
  };

  export type NullsOrder = (typeof NullsOrder)[keyof typeof NullsOrder]


  export const UserOrderByRelevanceFieldEnum: {
    wallet_address: 'wallet_address'
  };

  export type UserOrderByRelevanceFieldEnum = (typeof UserOrderByRelevanceFieldEnum)[keyof typeof UserOrderByRelevanceFieldEnum]


  export const ModelOrderByRelevanceFieldEnum: {
    model_name: 'model_name',
    description: 'description',
    wallet_address: 'wallet_address'
  };

  export type ModelOrderByRelevanceFieldEnum = (typeof ModelOrderByRelevanceFieldEnum)[keyof typeof ModelOrderByRelevanceFieldEnum]


  /**
   * Field references
   */


  /**
   * Reference to a field of type 'Int'
   */
  export type IntFieldRefInput<$PrismaModel> = FieldRefInputType<$PrismaModel, 'Int'>
    


  /**
   * Reference to a field of type 'String'
   */
  export type StringFieldRefInput<$PrismaModel> = FieldRefInputType<$PrismaModel, 'String'>
    


  /**
   * Reference to a field of type 'DateTime'
   */
  export type DateTimeFieldRefInput<$PrismaModel> = FieldRefInputType<$PrismaModel, 'DateTime'>
    


  /**
   * Reference to a field of type 'Boolean'
   */
  export type BooleanFieldRefInput<$PrismaModel> = FieldRefInputType<$PrismaModel, 'Boolean'>
    


  /**
   * Reference to a field of type 'Float'
   */
  export type FloatFieldRefInput<$PrismaModel> = FieldRefInputType<$PrismaModel, 'Float'>
    
  /**
   * Deep Input Types
   */


  export type UserWhereInput = {
    AND?: UserWhereInput | UserWhereInput[]
    OR?: UserWhereInput[]
    NOT?: UserWhereInput | UserWhereInput[]
    id?: IntFilter<"User"> | number
    wallet_address?: StringFilter<"User"> | string
    created_at?: DateTimeNullableFilter<"User"> | Date | string | null
    licenses?: LicenseListRelationFilter
  }

  export type UserOrderByWithRelationInput = {
    id?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrderInput | SortOrder
    licenses?: LicenseOrderByRelationAggregateInput
    _relevance?: UserOrderByRelevanceInput
  }

  export type UserWhereUniqueInput = Prisma.AtLeast<{
    id?: number
    wallet_address?: string
    AND?: UserWhereInput | UserWhereInput[]
    OR?: UserWhereInput[]
    NOT?: UserWhereInput | UserWhereInput[]
    created_at?: DateTimeNullableFilter<"User"> | Date | string | null
    licenses?: LicenseListRelationFilter
  }, "id" | "wallet_address">

  export type UserOrderByWithAggregationInput = {
    id?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrderInput | SortOrder
    _count?: UserCountOrderByAggregateInput
    _avg?: UserAvgOrderByAggregateInput
    _max?: UserMaxOrderByAggregateInput
    _min?: UserMinOrderByAggregateInput
    _sum?: UserSumOrderByAggregateInput
  }

  export type UserScalarWhereWithAggregatesInput = {
    AND?: UserScalarWhereWithAggregatesInput | UserScalarWhereWithAggregatesInput[]
    OR?: UserScalarWhereWithAggregatesInput[]
    NOT?: UserScalarWhereWithAggregatesInput | UserScalarWhereWithAggregatesInput[]
    id?: IntWithAggregatesFilter<"User"> | number
    wallet_address?: StringWithAggregatesFilter<"User"> | string
    created_at?: DateTimeNullableWithAggregatesFilter<"User"> | Date | string | null
  }

  export type ModelWhereInput = {
    AND?: ModelWhereInput | ModelWhereInput[]
    OR?: ModelWhereInput[]
    NOT?: ModelWhereInput | ModelWhereInput[]
    id?: IntFilter<"Model"> | number
    model_name?: StringFilter<"Model"> | string
    description?: StringFilter<"Model"> | string
    is_derivative?: BoolFilter<"Model"> | boolean
    royalty?: IntFilter<"Model"> | number
    wallet_address?: StringFilter<"Model"> | string
    created_at?: DateTimeFilter<"Model"> | Date | string
    licenses?: LicenseListRelationFilter
  }

  export type ModelOrderByWithRelationInput = {
    id?: SortOrder
    model_name?: SortOrder
    description?: SortOrder
    is_derivative?: SortOrder
    royalty?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
    licenses?: LicenseOrderByRelationAggregateInput
    _relevance?: ModelOrderByRelevanceInput
  }

  export type ModelWhereUniqueInput = Prisma.AtLeast<{
    id?: number
    AND?: ModelWhereInput | ModelWhereInput[]
    OR?: ModelWhereInput[]
    NOT?: ModelWhereInput | ModelWhereInput[]
    model_name?: StringFilter<"Model"> | string
    description?: StringFilter<"Model"> | string
    is_derivative?: BoolFilter<"Model"> | boolean
    royalty?: IntFilter<"Model"> | number
    wallet_address?: StringFilter<"Model"> | string
    created_at?: DateTimeFilter<"Model"> | Date | string
    licenses?: LicenseListRelationFilter
  }, "id">

  export type ModelOrderByWithAggregationInput = {
    id?: SortOrder
    model_name?: SortOrder
    description?: SortOrder
    is_derivative?: SortOrder
    royalty?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
    _count?: ModelCountOrderByAggregateInput
    _avg?: ModelAvgOrderByAggregateInput
    _max?: ModelMaxOrderByAggregateInput
    _min?: ModelMinOrderByAggregateInput
    _sum?: ModelSumOrderByAggregateInput
  }

  export type ModelScalarWhereWithAggregatesInput = {
    AND?: ModelScalarWhereWithAggregatesInput | ModelScalarWhereWithAggregatesInput[]
    OR?: ModelScalarWhereWithAggregatesInput[]
    NOT?: ModelScalarWhereWithAggregatesInput | ModelScalarWhereWithAggregatesInput[]
    id?: IntWithAggregatesFilter<"Model"> | number
    model_name?: StringWithAggregatesFilter<"Model"> | string
    description?: StringWithAggregatesFilter<"Model"> | string
    is_derivative?: BoolWithAggregatesFilter<"Model"> | boolean
    royalty?: IntWithAggregatesFilter<"Model"> | number
    wallet_address?: StringWithAggregatesFilter<"Model"> | string
    created_at?: DateTimeWithAggregatesFilter<"Model"> | Date | string
  }

  export type LicenseWhereInput = {
    AND?: LicenseWhereInput | LicenseWhereInput[]
    OR?: LicenseWhereInput[]
    NOT?: LicenseWhereInput | LicenseWhereInput[]
    id?: IntFilter<"License"> | number
    userId?: IntFilter<"License"> | number
    modelId?: IntFilter<"License"> | number
    created_at?: DateTimeFilter<"License"> | Date | string
    user?: XOR<UserScalarRelationFilter, UserWhereInput>
    model?: XOR<ModelScalarRelationFilter, ModelWhereInput>
  }

  export type LicenseOrderByWithRelationInput = {
    id?: SortOrder
    userId?: SortOrder
    modelId?: SortOrder
    created_at?: SortOrder
    user?: UserOrderByWithRelationInput
    model?: ModelOrderByWithRelationInput
  }

  export type LicenseWhereUniqueInput = Prisma.AtLeast<{
    id?: number
    userId_modelId?: LicenseUserIdModelIdCompoundUniqueInput
    AND?: LicenseWhereInput | LicenseWhereInput[]
    OR?: LicenseWhereInput[]
    NOT?: LicenseWhereInput | LicenseWhereInput[]
    userId?: IntFilter<"License"> | number
    modelId?: IntFilter<"License"> | number
    created_at?: DateTimeFilter<"License"> | Date | string
    user?: XOR<UserScalarRelationFilter, UserWhereInput>
    model?: XOR<ModelScalarRelationFilter, ModelWhereInput>
  }, "id" | "userId_modelId">

  export type LicenseOrderByWithAggregationInput = {
    id?: SortOrder
    userId?: SortOrder
    modelId?: SortOrder
    created_at?: SortOrder
    _count?: LicenseCountOrderByAggregateInput
    _avg?: LicenseAvgOrderByAggregateInput
    _max?: LicenseMaxOrderByAggregateInput
    _min?: LicenseMinOrderByAggregateInput
    _sum?: LicenseSumOrderByAggregateInput
  }

  export type LicenseScalarWhereWithAggregatesInput = {
    AND?: LicenseScalarWhereWithAggregatesInput | LicenseScalarWhereWithAggregatesInput[]
    OR?: LicenseScalarWhereWithAggregatesInput[]
    NOT?: LicenseScalarWhereWithAggregatesInput | LicenseScalarWhereWithAggregatesInput[]
    id?: IntWithAggregatesFilter<"License"> | number
    userId?: IntWithAggregatesFilter<"License"> | number
    modelId?: IntWithAggregatesFilter<"License"> | number
    created_at?: DateTimeWithAggregatesFilter<"License"> | Date | string
  }

  export type UserCreateInput = {
    wallet_address: string
    created_at?: Date | string | null
    licenses?: LicenseCreateNestedManyWithoutUserInput
  }

  export type UserUncheckedCreateInput = {
    id?: number
    wallet_address: string
    created_at?: Date | string | null
    licenses?: LicenseUncheckedCreateNestedManyWithoutUserInput
  }

  export type UserUpdateInput = {
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: NullableDateTimeFieldUpdateOperationsInput | Date | string | null
    licenses?: LicenseUpdateManyWithoutUserNestedInput
  }

  export type UserUncheckedUpdateInput = {
    id?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: NullableDateTimeFieldUpdateOperationsInput | Date | string | null
    licenses?: LicenseUncheckedUpdateManyWithoutUserNestedInput
  }

  export type UserCreateManyInput = {
    id?: number
    wallet_address: string
    created_at?: Date | string | null
  }

  export type UserUpdateManyMutationInput = {
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: NullableDateTimeFieldUpdateOperationsInput | Date | string | null
  }

  export type UserUncheckedUpdateManyInput = {
    id?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: NullableDateTimeFieldUpdateOperationsInput | Date | string | null
  }

  export type ModelCreateInput = {
    model_name: string
    description: string
    is_derivative: boolean
    royalty: number
    wallet_address: string
    created_at?: Date | string
    licenses?: LicenseCreateNestedManyWithoutModelInput
  }

  export type ModelUncheckedCreateInput = {
    id?: number
    model_name: string
    description: string
    is_derivative: boolean
    royalty: number
    wallet_address: string
    created_at?: Date | string
    licenses?: LicenseUncheckedCreateNestedManyWithoutModelInput
  }

  export type ModelUpdateInput = {
    model_name?: StringFieldUpdateOperationsInput | string
    description?: StringFieldUpdateOperationsInput | string
    is_derivative?: BoolFieldUpdateOperationsInput | boolean
    royalty?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
    licenses?: LicenseUpdateManyWithoutModelNestedInput
  }

  export type ModelUncheckedUpdateInput = {
    id?: IntFieldUpdateOperationsInput | number
    model_name?: StringFieldUpdateOperationsInput | string
    description?: StringFieldUpdateOperationsInput | string
    is_derivative?: BoolFieldUpdateOperationsInput | boolean
    royalty?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
    licenses?: LicenseUncheckedUpdateManyWithoutModelNestedInput
  }

  export type ModelCreateManyInput = {
    id?: number
    model_name: string
    description: string
    is_derivative: boolean
    royalty: number
    wallet_address: string
    created_at?: Date | string
  }

  export type ModelUpdateManyMutationInput = {
    model_name?: StringFieldUpdateOperationsInput | string
    description?: StringFieldUpdateOperationsInput | string
    is_derivative?: BoolFieldUpdateOperationsInput | boolean
    royalty?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type ModelUncheckedUpdateManyInput = {
    id?: IntFieldUpdateOperationsInput | number
    model_name?: StringFieldUpdateOperationsInput | string
    description?: StringFieldUpdateOperationsInput | string
    is_derivative?: BoolFieldUpdateOperationsInput | boolean
    royalty?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type LicenseCreateInput = {
    created_at?: Date | string
    user: UserCreateNestedOneWithoutLicensesInput
    model: ModelCreateNestedOneWithoutLicensesInput
  }

  export type LicenseUncheckedCreateInput = {
    id?: number
    userId: number
    modelId: number
    created_at?: Date | string
  }

  export type LicenseUpdateInput = {
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
    user?: UserUpdateOneRequiredWithoutLicensesNestedInput
    model?: ModelUpdateOneRequiredWithoutLicensesNestedInput
  }

  export type LicenseUncheckedUpdateInput = {
    id?: IntFieldUpdateOperationsInput | number
    userId?: IntFieldUpdateOperationsInput | number
    modelId?: IntFieldUpdateOperationsInput | number
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type LicenseCreateManyInput = {
    id?: number
    userId: number
    modelId: number
    created_at?: Date | string
  }

  export type LicenseUpdateManyMutationInput = {
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type LicenseUncheckedUpdateManyInput = {
    id?: IntFieldUpdateOperationsInput | number
    userId?: IntFieldUpdateOperationsInput | number
    modelId?: IntFieldUpdateOperationsInput | number
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type IntFilter<$PrismaModel = never> = {
    equals?: number | IntFieldRefInput<$PrismaModel>
    in?: number[]
    notIn?: number[]
    lt?: number | IntFieldRefInput<$PrismaModel>
    lte?: number | IntFieldRefInput<$PrismaModel>
    gt?: number | IntFieldRefInput<$PrismaModel>
    gte?: number | IntFieldRefInput<$PrismaModel>
    not?: NestedIntFilter<$PrismaModel> | number
  }

  export type StringFilter<$PrismaModel = never> = {
    equals?: string | StringFieldRefInput<$PrismaModel>
    in?: string[]
    notIn?: string[]
    lt?: string | StringFieldRefInput<$PrismaModel>
    lte?: string | StringFieldRefInput<$PrismaModel>
    gt?: string | StringFieldRefInput<$PrismaModel>
    gte?: string | StringFieldRefInput<$PrismaModel>
    contains?: string | StringFieldRefInput<$PrismaModel>
    startsWith?: string | StringFieldRefInput<$PrismaModel>
    endsWith?: string | StringFieldRefInput<$PrismaModel>
    search?: string
    not?: NestedStringFilter<$PrismaModel> | string
  }

  export type DateTimeNullableFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel> | null
    in?: Date[] | string[] | null
    notIn?: Date[] | string[] | null
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeNullableFilter<$PrismaModel> | Date | string | null
  }

  export type LicenseListRelationFilter = {
    every?: LicenseWhereInput
    some?: LicenseWhereInput
    none?: LicenseWhereInput
  }

  export type SortOrderInput = {
    sort: SortOrder
    nulls?: NullsOrder
  }

  export type LicenseOrderByRelationAggregateInput = {
    _count?: SortOrder
  }

  export type UserOrderByRelevanceInput = {
    fields: UserOrderByRelevanceFieldEnum | UserOrderByRelevanceFieldEnum[]
    sort: SortOrder
    search: string
  }

  export type UserCountOrderByAggregateInput = {
    id?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
  }

  export type UserAvgOrderByAggregateInput = {
    id?: SortOrder
  }

  export type UserMaxOrderByAggregateInput = {
    id?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
  }

  export type UserMinOrderByAggregateInput = {
    id?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
  }

  export type UserSumOrderByAggregateInput = {
    id?: SortOrder
  }

  export type IntWithAggregatesFilter<$PrismaModel = never> = {
    equals?: number | IntFieldRefInput<$PrismaModel>
    in?: number[]
    notIn?: number[]
    lt?: number | IntFieldRefInput<$PrismaModel>
    lte?: number | IntFieldRefInput<$PrismaModel>
    gt?: number | IntFieldRefInput<$PrismaModel>
    gte?: number | IntFieldRefInput<$PrismaModel>
    not?: NestedIntWithAggregatesFilter<$PrismaModel> | number
    _count?: NestedIntFilter<$PrismaModel>
    _avg?: NestedFloatFilter<$PrismaModel>
    _sum?: NestedIntFilter<$PrismaModel>
    _min?: NestedIntFilter<$PrismaModel>
    _max?: NestedIntFilter<$PrismaModel>
  }

  export type StringWithAggregatesFilter<$PrismaModel = never> = {
    equals?: string | StringFieldRefInput<$PrismaModel>
    in?: string[]
    notIn?: string[]
    lt?: string | StringFieldRefInput<$PrismaModel>
    lte?: string | StringFieldRefInput<$PrismaModel>
    gt?: string | StringFieldRefInput<$PrismaModel>
    gte?: string | StringFieldRefInput<$PrismaModel>
    contains?: string | StringFieldRefInput<$PrismaModel>
    startsWith?: string | StringFieldRefInput<$PrismaModel>
    endsWith?: string | StringFieldRefInput<$PrismaModel>
    search?: string
    not?: NestedStringWithAggregatesFilter<$PrismaModel> | string
    _count?: NestedIntFilter<$PrismaModel>
    _min?: NestedStringFilter<$PrismaModel>
    _max?: NestedStringFilter<$PrismaModel>
  }

  export type DateTimeNullableWithAggregatesFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel> | null
    in?: Date[] | string[] | null
    notIn?: Date[] | string[] | null
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeNullableWithAggregatesFilter<$PrismaModel> | Date | string | null
    _count?: NestedIntNullableFilter<$PrismaModel>
    _min?: NestedDateTimeNullableFilter<$PrismaModel>
    _max?: NestedDateTimeNullableFilter<$PrismaModel>
  }

  export type BoolFilter<$PrismaModel = never> = {
    equals?: boolean | BooleanFieldRefInput<$PrismaModel>
    not?: NestedBoolFilter<$PrismaModel> | boolean
  }

  export type DateTimeFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    in?: Date[] | string[]
    notIn?: Date[] | string[]
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeFilter<$PrismaModel> | Date | string
  }

  export type ModelOrderByRelevanceInput = {
    fields: ModelOrderByRelevanceFieldEnum | ModelOrderByRelevanceFieldEnum[]
    sort: SortOrder
    search: string
  }

  export type ModelCountOrderByAggregateInput = {
    id?: SortOrder
    model_name?: SortOrder
    description?: SortOrder
    is_derivative?: SortOrder
    royalty?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
  }

  export type ModelAvgOrderByAggregateInput = {
    id?: SortOrder
    royalty?: SortOrder
  }

  export type ModelMaxOrderByAggregateInput = {
    id?: SortOrder
    model_name?: SortOrder
    description?: SortOrder
    is_derivative?: SortOrder
    royalty?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
  }

  export type ModelMinOrderByAggregateInput = {
    id?: SortOrder
    model_name?: SortOrder
    description?: SortOrder
    is_derivative?: SortOrder
    royalty?: SortOrder
    wallet_address?: SortOrder
    created_at?: SortOrder
  }

  export type ModelSumOrderByAggregateInput = {
    id?: SortOrder
    royalty?: SortOrder
  }

  export type BoolWithAggregatesFilter<$PrismaModel = never> = {
    equals?: boolean | BooleanFieldRefInput<$PrismaModel>
    not?: NestedBoolWithAggregatesFilter<$PrismaModel> | boolean
    _count?: NestedIntFilter<$PrismaModel>
    _min?: NestedBoolFilter<$PrismaModel>
    _max?: NestedBoolFilter<$PrismaModel>
  }

  export type DateTimeWithAggregatesFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    in?: Date[] | string[]
    notIn?: Date[] | string[]
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeWithAggregatesFilter<$PrismaModel> | Date | string
    _count?: NestedIntFilter<$PrismaModel>
    _min?: NestedDateTimeFilter<$PrismaModel>
    _max?: NestedDateTimeFilter<$PrismaModel>
  }

  export type UserScalarRelationFilter = {
    is?: UserWhereInput
    isNot?: UserWhereInput
  }

  export type ModelScalarRelationFilter = {
    is?: ModelWhereInput
    isNot?: ModelWhereInput
  }

  export type LicenseUserIdModelIdCompoundUniqueInput = {
    userId: number
    modelId: number
  }

  export type LicenseCountOrderByAggregateInput = {
    id?: SortOrder
    userId?: SortOrder
    modelId?: SortOrder
    created_at?: SortOrder
  }

  export type LicenseAvgOrderByAggregateInput = {
    id?: SortOrder
    userId?: SortOrder
    modelId?: SortOrder
  }

  export type LicenseMaxOrderByAggregateInput = {
    id?: SortOrder
    userId?: SortOrder
    modelId?: SortOrder
    created_at?: SortOrder
  }

  export type LicenseMinOrderByAggregateInput = {
    id?: SortOrder
    userId?: SortOrder
    modelId?: SortOrder
    created_at?: SortOrder
  }

  export type LicenseSumOrderByAggregateInput = {
    id?: SortOrder
    userId?: SortOrder
    modelId?: SortOrder
  }

  export type LicenseCreateNestedManyWithoutUserInput = {
    create?: XOR<LicenseCreateWithoutUserInput, LicenseUncheckedCreateWithoutUserInput> | LicenseCreateWithoutUserInput[] | LicenseUncheckedCreateWithoutUserInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutUserInput | LicenseCreateOrConnectWithoutUserInput[]
    createMany?: LicenseCreateManyUserInputEnvelope
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
  }

  export type LicenseUncheckedCreateNestedManyWithoutUserInput = {
    create?: XOR<LicenseCreateWithoutUserInput, LicenseUncheckedCreateWithoutUserInput> | LicenseCreateWithoutUserInput[] | LicenseUncheckedCreateWithoutUserInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutUserInput | LicenseCreateOrConnectWithoutUserInput[]
    createMany?: LicenseCreateManyUserInputEnvelope
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
  }

  export type StringFieldUpdateOperationsInput = {
    set?: string
  }

  export type NullableDateTimeFieldUpdateOperationsInput = {
    set?: Date | string | null
  }

  export type LicenseUpdateManyWithoutUserNestedInput = {
    create?: XOR<LicenseCreateWithoutUserInput, LicenseUncheckedCreateWithoutUserInput> | LicenseCreateWithoutUserInput[] | LicenseUncheckedCreateWithoutUserInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutUserInput | LicenseCreateOrConnectWithoutUserInput[]
    upsert?: LicenseUpsertWithWhereUniqueWithoutUserInput | LicenseUpsertWithWhereUniqueWithoutUserInput[]
    createMany?: LicenseCreateManyUserInputEnvelope
    set?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    disconnect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    delete?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    update?: LicenseUpdateWithWhereUniqueWithoutUserInput | LicenseUpdateWithWhereUniqueWithoutUserInput[]
    updateMany?: LicenseUpdateManyWithWhereWithoutUserInput | LicenseUpdateManyWithWhereWithoutUserInput[]
    deleteMany?: LicenseScalarWhereInput | LicenseScalarWhereInput[]
  }

  export type IntFieldUpdateOperationsInput = {
    set?: number
    increment?: number
    decrement?: number
    multiply?: number
    divide?: number
  }

  export type LicenseUncheckedUpdateManyWithoutUserNestedInput = {
    create?: XOR<LicenseCreateWithoutUserInput, LicenseUncheckedCreateWithoutUserInput> | LicenseCreateWithoutUserInput[] | LicenseUncheckedCreateWithoutUserInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutUserInput | LicenseCreateOrConnectWithoutUserInput[]
    upsert?: LicenseUpsertWithWhereUniqueWithoutUserInput | LicenseUpsertWithWhereUniqueWithoutUserInput[]
    createMany?: LicenseCreateManyUserInputEnvelope
    set?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    disconnect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    delete?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    update?: LicenseUpdateWithWhereUniqueWithoutUserInput | LicenseUpdateWithWhereUniqueWithoutUserInput[]
    updateMany?: LicenseUpdateManyWithWhereWithoutUserInput | LicenseUpdateManyWithWhereWithoutUserInput[]
    deleteMany?: LicenseScalarWhereInput | LicenseScalarWhereInput[]
  }

  export type LicenseCreateNestedManyWithoutModelInput = {
    create?: XOR<LicenseCreateWithoutModelInput, LicenseUncheckedCreateWithoutModelInput> | LicenseCreateWithoutModelInput[] | LicenseUncheckedCreateWithoutModelInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutModelInput | LicenseCreateOrConnectWithoutModelInput[]
    createMany?: LicenseCreateManyModelInputEnvelope
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
  }

  export type LicenseUncheckedCreateNestedManyWithoutModelInput = {
    create?: XOR<LicenseCreateWithoutModelInput, LicenseUncheckedCreateWithoutModelInput> | LicenseCreateWithoutModelInput[] | LicenseUncheckedCreateWithoutModelInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutModelInput | LicenseCreateOrConnectWithoutModelInput[]
    createMany?: LicenseCreateManyModelInputEnvelope
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
  }

  export type BoolFieldUpdateOperationsInput = {
    set?: boolean
  }

  export type DateTimeFieldUpdateOperationsInput = {
    set?: Date | string
  }

  export type LicenseUpdateManyWithoutModelNestedInput = {
    create?: XOR<LicenseCreateWithoutModelInput, LicenseUncheckedCreateWithoutModelInput> | LicenseCreateWithoutModelInput[] | LicenseUncheckedCreateWithoutModelInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutModelInput | LicenseCreateOrConnectWithoutModelInput[]
    upsert?: LicenseUpsertWithWhereUniqueWithoutModelInput | LicenseUpsertWithWhereUniqueWithoutModelInput[]
    createMany?: LicenseCreateManyModelInputEnvelope
    set?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    disconnect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    delete?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    update?: LicenseUpdateWithWhereUniqueWithoutModelInput | LicenseUpdateWithWhereUniqueWithoutModelInput[]
    updateMany?: LicenseUpdateManyWithWhereWithoutModelInput | LicenseUpdateManyWithWhereWithoutModelInput[]
    deleteMany?: LicenseScalarWhereInput | LicenseScalarWhereInput[]
  }

  export type LicenseUncheckedUpdateManyWithoutModelNestedInput = {
    create?: XOR<LicenseCreateWithoutModelInput, LicenseUncheckedCreateWithoutModelInput> | LicenseCreateWithoutModelInput[] | LicenseUncheckedCreateWithoutModelInput[]
    connectOrCreate?: LicenseCreateOrConnectWithoutModelInput | LicenseCreateOrConnectWithoutModelInput[]
    upsert?: LicenseUpsertWithWhereUniqueWithoutModelInput | LicenseUpsertWithWhereUniqueWithoutModelInput[]
    createMany?: LicenseCreateManyModelInputEnvelope
    set?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    disconnect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    delete?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    connect?: LicenseWhereUniqueInput | LicenseWhereUniqueInput[]
    update?: LicenseUpdateWithWhereUniqueWithoutModelInput | LicenseUpdateWithWhereUniqueWithoutModelInput[]
    updateMany?: LicenseUpdateManyWithWhereWithoutModelInput | LicenseUpdateManyWithWhereWithoutModelInput[]
    deleteMany?: LicenseScalarWhereInput | LicenseScalarWhereInput[]
  }

  export type UserCreateNestedOneWithoutLicensesInput = {
    create?: XOR<UserCreateWithoutLicensesInput, UserUncheckedCreateWithoutLicensesInput>
    connectOrCreate?: UserCreateOrConnectWithoutLicensesInput
    connect?: UserWhereUniqueInput
  }

  export type ModelCreateNestedOneWithoutLicensesInput = {
    create?: XOR<ModelCreateWithoutLicensesInput, ModelUncheckedCreateWithoutLicensesInput>
    connectOrCreate?: ModelCreateOrConnectWithoutLicensesInput
    connect?: ModelWhereUniqueInput
  }

  export type UserUpdateOneRequiredWithoutLicensesNestedInput = {
    create?: XOR<UserCreateWithoutLicensesInput, UserUncheckedCreateWithoutLicensesInput>
    connectOrCreate?: UserCreateOrConnectWithoutLicensesInput
    upsert?: UserUpsertWithoutLicensesInput
    connect?: UserWhereUniqueInput
    update?: XOR<XOR<UserUpdateToOneWithWhereWithoutLicensesInput, UserUpdateWithoutLicensesInput>, UserUncheckedUpdateWithoutLicensesInput>
  }

  export type ModelUpdateOneRequiredWithoutLicensesNestedInput = {
    create?: XOR<ModelCreateWithoutLicensesInput, ModelUncheckedCreateWithoutLicensesInput>
    connectOrCreate?: ModelCreateOrConnectWithoutLicensesInput
    upsert?: ModelUpsertWithoutLicensesInput
    connect?: ModelWhereUniqueInput
    update?: XOR<XOR<ModelUpdateToOneWithWhereWithoutLicensesInput, ModelUpdateWithoutLicensesInput>, ModelUncheckedUpdateWithoutLicensesInput>
  }

  export type NestedIntFilter<$PrismaModel = never> = {
    equals?: number | IntFieldRefInput<$PrismaModel>
    in?: number[]
    notIn?: number[]
    lt?: number | IntFieldRefInput<$PrismaModel>
    lte?: number | IntFieldRefInput<$PrismaModel>
    gt?: number | IntFieldRefInput<$PrismaModel>
    gte?: number | IntFieldRefInput<$PrismaModel>
    not?: NestedIntFilter<$PrismaModel> | number
  }

  export type NestedStringFilter<$PrismaModel = never> = {
    equals?: string | StringFieldRefInput<$PrismaModel>
    in?: string[]
    notIn?: string[]
    lt?: string | StringFieldRefInput<$PrismaModel>
    lte?: string | StringFieldRefInput<$PrismaModel>
    gt?: string | StringFieldRefInput<$PrismaModel>
    gte?: string | StringFieldRefInput<$PrismaModel>
    contains?: string | StringFieldRefInput<$PrismaModel>
    startsWith?: string | StringFieldRefInput<$PrismaModel>
    endsWith?: string | StringFieldRefInput<$PrismaModel>
    search?: string
    not?: NestedStringFilter<$PrismaModel> | string
  }

  export type NestedDateTimeNullableFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel> | null
    in?: Date[] | string[] | null
    notIn?: Date[] | string[] | null
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeNullableFilter<$PrismaModel> | Date | string | null
  }

  export type NestedIntWithAggregatesFilter<$PrismaModel = never> = {
    equals?: number | IntFieldRefInput<$PrismaModel>
    in?: number[]
    notIn?: number[]
    lt?: number | IntFieldRefInput<$PrismaModel>
    lte?: number | IntFieldRefInput<$PrismaModel>
    gt?: number | IntFieldRefInput<$PrismaModel>
    gte?: number | IntFieldRefInput<$PrismaModel>
    not?: NestedIntWithAggregatesFilter<$PrismaModel> | number
    _count?: NestedIntFilter<$PrismaModel>
    _avg?: NestedFloatFilter<$PrismaModel>
    _sum?: NestedIntFilter<$PrismaModel>
    _min?: NestedIntFilter<$PrismaModel>
    _max?: NestedIntFilter<$PrismaModel>
  }

  export type NestedFloatFilter<$PrismaModel = never> = {
    equals?: number | FloatFieldRefInput<$PrismaModel>
    in?: number[]
    notIn?: number[]
    lt?: number | FloatFieldRefInput<$PrismaModel>
    lte?: number | FloatFieldRefInput<$PrismaModel>
    gt?: number | FloatFieldRefInput<$PrismaModel>
    gte?: number | FloatFieldRefInput<$PrismaModel>
    not?: NestedFloatFilter<$PrismaModel> | number
  }

  export type NestedStringWithAggregatesFilter<$PrismaModel = never> = {
    equals?: string | StringFieldRefInput<$PrismaModel>
    in?: string[]
    notIn?: string[]
    lt?: string | StringFieldRefInput<$PrismaModel>
    lte?: string | StringFieldRefInput<$PrismaModel>
    gt?: string | StringFieldRefInput<$PrismaModel>
    gte?: string | StringFieldRefInput<$PrismaModel>
    contains?: string | StringFieldRefInput<$PrismaModel>
    startsWith?: string | StringFieldRefInput<$PrismaModel>
    endsWith?: string | StringFieldRefInput<$PrismaModel>
    search?: string
    not?: NestedStringWithAggregatesFilter<$PrismaModel> | string
    _count?: NestedIntFilter<$PrismaModel>
    _min?: NestedStringFilter<$PrismaModel>
    _max?: NestedStringFilter<$PrismaModel>
  }

  export type NestedDateTimeNullableWithAggregatesFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel> | null
    in?: Date[] | string[] | null
    notIn?: Date[] | string[] | null
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeNullableWithAggregatesFilter<$PrismaModel> | Date | string | null
    _count?: NestedIntNullableFilter<$PrismaModel>
    _min?: NestedDateTimeNullableFilter<$PrismaModel>
    _max?: NestedDateTimeNullableFilter<$PrismaModel>
  }

  export type NestedIntNullableFilter<$PrismaModel = never> = {
    equals?: number | IntFieldRefInput<$PrismaModel> | null
    in?: number[] | null
    notIn?: number[] | null
    lt?: number | IntFieldRefInput<$PrismaModel>
    lte?: number | IntFieldRefInput<$PrismaModel>
    gt?: number | IntFieldRefInput<$PrismaModel>
    gte?: number | IntFieldRefInput<$PrismaModel>
    not?: NestedIntNullableFilter<$PrismaModel> | number | null
  }

  export type NestedBoolFilter<$PrismaModel = never> = {
    equals?: boolean | BooleanFieldRefInput<$PrismaModel>
    not?: NestedBoolFilter<$PrismaModel> | boolean
  }

  export type NestedDateTimeFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    in?: Date[] | string[]
    notIn?: Date[] | string[]
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeFilter<$PrismaModel> | Date | string
  }

  export type NestedBoolWithAggregatesFilter<$PrismaModel = never> = {
    equals?: boolean | BooleanFieldRefInput<$PrismaModel>
    not?: NestedBoolWithAggregatesFilter<$PrismaModel> | boolean
    _count?: NestedIntFilter<$PrismaModel>
    _min?: NestedBoolFilter<$PrismaModel>
    _max?: NestedBoolFilter<$PrismaModel>
  }

  export type NestedDateTimeWithAggregatesFilter<$PrismaModel = never> = {
    equals?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    in?: Date[] | string[]
    notIn?: Date[] | string[]
    lt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    lte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gt?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    gte?: Date | string | DateTimeFieldRefInput<$PrismaModel>
    not?: NestedDateTimeWithAggregatesFilter<$PrismaModel> | Date | string
    _count?: NestedIntFilter<$PrismaModel>
    _min?: NestedDateTimeFilter<$PrismaModel>
    _max?: NestedDateTimeFilter<$PrismaModel>
  }

  export type LicenseCreateWithoutUserInput = {
    created_at?: Date | string
    model: ModelCreateNestedOneWithoutLicensesInput
  }

  export type LicenseUncheckedCreateWithoutUserInput = {
    id?: number
    modelId: number
    created_at?: Date | string
  }

  export type LicenseCreateOrConnectWithoutUserInput = {
    where: LicenseWhereUniqueInput
    create: XOR<LicenseCreateWithoutUserInput, LicenseUncheckedCreateWithoutUserInput>
  }

  export type LicenseCreateManyUserInputEnvelope = {
    data: LicenseCreateManyUserInput | LicenseCreateManyUserInput[]
    skipDuplicates?: boolean
  }

  export type LicenseUpsertWithWhereUniqueWithoutUserInput = {
    where: LicenseWhereUniqueInput
    update: XOR<LicenseUpdateWithoutUserInput, LicenseUncheckedUpdateWithoutUserInput>
    create: XOR<LicenseCreateWithoutUserInput, LicenseUncheckedCreateWithoutUserInput>
  }

  export type LicenseUpdateWithWhereUniqueWithoutUserInput = {
    where: LicenseWhereUniqueInput
    data: XOR<LicenseUpdateWithoutUserInput, LicenseUncheckedUpdateWithoutUserInput>
  }

  export type LicenseUpdateManyWithWhereWithoutUserInput = {
    where: LicenseScalarWhereInput
    data: XOR<LicenseUpdateManyMutationInput, LicenseUncheckedUpdateManyWithoutUserInput>
  }

  export type LicenseScalarWhereInput = {
    AND?: LicenseScalarWhereInput | LicenseScalarWhereInput[]
    OR?: LicenseScalarWhereInput[]
    NOT?: LicenseScalarWhereInput | LicenseScalarWhereInput[]
    id?: IntFilter<"License"> | number
    userId?: IntFilter<"License"> | number
    modelId?: IntFilter<"License"> | number
    created_at?: DateTimeFilter<"License"> | Date | string
  }

  export type LicenseCreateWithoutModelInput = {
    created_at?: Date | string
    user: UserCreateNestedOneWithoutLicensesInput
  }

  export type LicenseUncheckedCreateWithoutModelInput = {
    id?: number
    userId: number
    created_at?: Date | string
  }

  export type LicenseCreateOrConnectWithoutModelInput = {
    where: LicenseWhereUniqueInput
    create: XOR<LicenseCreateWithoutModelInput, LicenseUncheckedCreateWithoutModelInput>
  }

  export type LicenseCreateManyModelInputEnvelope = {
    data: LicenseCreateManyModelInput | LicenseCreateManyModelInput[]
    skipDuplicates?: boolean
  }

  export type LicenseUpsertWithWhereUniqueWithoutModelInput = {
    where: LicenseWhereUniqueInput
    update: XOR<LicenseUpdateWithoutModelInput, LicenseUncheckedUpdateWithoutModelInput>
    create: XOR<LicenseCreateWithoutModelInput, LicenseUncheckedCreateWithoutModelInput>
  }

  export type LicenseUpdateWithWhereUniqueWithoutModelInput = {
    where: LicenseWhereUniqueInput
    data: XOR<LicenseUpdateWithoutModelInput, LicenseUncheckedUpdateWithoutModelInput>
  }

  export type LicenseUpdateManyWithWhereWithoutModelInput = {
    where: LicenseScalarWhereInput
    data: XOR<LicenseUpdateManyMutationInput, LicenseUncheckedUpdateManyWithoutModelInput>
  }

  export type UserCreateWithoutLicensesInput = {
    wallet_address: string
    created_at?: Date | string | null
  }

  export type UserUncheckedCreateWithoutLicensesInput = {
    id?: number
    wallet_address: string
    created_at?: Date | string | null
  }

  export type UserCreateOrConnectWithoutLicensesInput = {
    where: UserWhereUniqueInput
    create: XOR<UserCreateWithoutLicensesInput, UserUncheckedCreateWithoutLicensesInput>
  }

  export type ModelCreateWithoutLicensesInput = {
    model_name: string
    description: string
    is_derivative: boolean
    royalty: number
    wallet_address: string
    created_at?: Date | string
  }

  export type ModelUncheckedCreateWithoutLicensesInput = {
    id?: number
    model_name: string
    description: string
    is_derivative: boolean
    royalty: number
    wallet_address: string
    created_at?: Date | string
  }

  export type ModelCreateOrConnectWithoutLicensesInput = {
    where: ModelWhereUniqueInput
    create: XOR<ModelCreateWithoutLicensesInput, ModelUncheckedCreateWithoutLicensesInput>
  }

  export type UserUpsertWithoutLicensesInput = {
    update: XOR<UserUpdateWithoutLicensesInput, UserUncheckedUpdateWithoutLicensesInput>
    create: XOR<UserCreateWithoutLicensesInput, UserUncheckedCreateWithoutLicensesInput>
    where?: UserWhereInput
  }

  export type UserUpdateToOneWithWhereWithoutLicensesInput = {
    where?: UserWhereInput
    data: XOR<UserUpdateWithoutLicensesInput, UserUncheckedUpdateWithoutLicensesInput>
  }

  export type UserUpdateWithoutLicensesInput = {
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: NullableDateTimeFieldUpdateOperationsInput | Date | string | null
  }

  export type UserUncheckedUpdateWithoutLicensesInput = {
    id?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: NullableDateTimeFieldUpdateOperationsInput | Date | string | null
  }

  export type ModelUpsertWithoutLicensesInput = {
    update: XOR<ModelUpdateWithoutLicensesInput, ModelUncheckedUpdateWithoutLicensesInput>
    create: XOR<ModelCreateWithoutLicensesInput, ModelUncheckedCreateWithoutLicensesInput>
    where?: ModelWhereInput
  }

  export type ModelUpdateToOneWithWhereWithoutLicensesInput = {
    where?: ModelWhereInput
    data: XOR<ModelUpdateWithoutLicensesInput, ModelUncheckedUpdateWithoutLicensesInput>
  }

  export type ModelUpdateWithoutLicensesInput = {
    model_name?: StringFieldUpdateOperationsInput | string
    description?: StringFieldUpdateOperationsInput | string
    is_derivative?: BoolFieldUpdateOperationsInput | boolean
    royalty?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type ModelUncheckedUpdateWithoutLicensesInput = {
    id?: IntFieldUpdateOperationsInput | number
    model_name?: StringFieldUpdateOperationsInput | string
    description?: StringFieldUpdateOperationsInput | string
    is_derivative?: BoolFieldUpdateOperationsInput | boolean
    royalty?: IntFieldUpdateOperationsInput | number
    wallet_address?: StringFieldUpdateOperationsInput | string
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type LicenseCreateManyUserInput = {
    id?: number
    modelId: number
    created_at?: Date | string
  }

  export type LicenseUpdateWithoutUserInput = {
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
    model?: ModelUpdateOneRequiredWithoutLicensesNestedInput
  }

  export type LicenseUncheckedUpdateWithoutUserInput = {
    id?: IntFieldUpdateOperationsInput | number
    modelId?: IntFieldUpdateOperationsInput | number
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type LicenseUncheckedUpdateManyWithoutUserInput = {
    id?: IntFieldUpdateOperationsInput | number
    modelId?: IntFieldUpdateOperationsInput | number
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type LicenseCreateManyModelInput = {
    id?: number
    userId: number
    created_at?: Date | string
  }

  export type LicenseUpdateWithoutModelInput = {
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
    user?: UserUpdateOneRequiredWithoutLicensesNestedInput
  }

  export type LicenseUncheckedUpdateWithoutModelInput = {
    id?: IntFieldUpdateOperationsInput | number
    userId?: IntFieldUpdateOperationsInput | number
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }

  export type LicenseUncheckedUpdateManyWithoutModelInput = {
    id?: IntFieldUpdateOperationsInput | number
    userId?: IntFieldUpdateOperationsInput | number
    created_at?: DateTimeFieldUpdateOperationsInput | Date | string
  }



  /**
   * Batch Payload for updateMany & deleteMany & createMany
   */

  export type BatchPayload = {
    count: number
  }

  /**
   * DMMF
   */
  export const dmmf: runtime.BaseDMMF
}